package asm.basic;

import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodAdapter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;


/**
 * 修改C.java的代码为
 public class C {
     public static long timer;
     public void m() throws InterruptedException{
         timer -= System.currentTimeMillis();
         Thread.sleep(100);
         timer += System.currentTimeMillis();
     }
 }
 * 计算消耗时间
 * 字节码比较变化
 * GETSTATIC C.timer : J
 * INVOKESTATIC java/lang/System.currentTimilis()J
 * LSUB
 * PUTSTATIC C.timer : J
 LDC 100
 INVOKESTATIC java/lang/Thread.sleep(J)V
 * GETSTATIC C.timer : J
 * INVOKESTATIC java/lang/System.currentTimilis()J
 * LADD
 * PUTSTATIC C.timer : J
 RETURN
 MAXSTACK=4
 MAXLOCALS=1
 */
public class AddTimeClassAdapter extends ClassAdapter {
    private String owner;
    private boolean isInterface;
    public AddTimeClassAdapter(ClassVisitor cv) {
        super(cv);
    }
    @Override
    public void visit(int version, int access, String name, String signature,
            String superName, String[] interfaces) {
        cv.visit(version, access, name, signature, superName, interfaces);
        owner = name;
        isInterface = (access & Opcodes.ACC_INTERFACE) != 0;
    }
    @Override
    public MethodVisitor visitMethod(int access, String name, String desc,
            String signature, String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
        if(!name.equals("<init>") && !isInterface && mv!=null){
            //为方法添加计时功能
            mv = new AddTimeMethodAdapter(mv);
        }
        return mv;
    }
    @Override
    public void visitEnd() {
        //添加字段
        if(!isInterface){
            FieldVisitor fv = cv.visitField(Opcodes.ACC_PUBLIC+Opcodes.ACC_STATIC, "timer", "J", null, null);
            if(fv!=null){
                fv.visitEnd();
            }
        }
        cv.visitEnd();
    }
    
    class AddTimeMethodAdapter extends MethodAdapter{
        public AddTimeMethodAdapter(MethodVisitor mv) {
            super(mv);
        }
        @Override
        public void visitCode() {
            mv.visitCode();
            mv.visitFieldInsn(Opcodes.GETSTATIC, owner, "timer", "J");
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J");
            mv.visitInsn(Opcodes.LSUB);
            mv.visitFieldInsn(Opcodes.PUTSTATIC, owner, "timer", "J");
        }
        @Override
        public void visitInsn(int opcode) {
            if((opcode>=Opcodes.IRETURN && opcode<=Opcodes.RETURN) || opcode==Opcodes.ATHROW){
                mv.visitFieldInsn(Opcodes.GETSTATIC, owner, "timer", "J");
                mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J");
                mv.visitInsn(Opcodes.LADD);
                mv.visitFieldInsn(Opcodes.PUTSTATIC, owner, "timer", "J");
            }
            mv.visitInsn(opcode);
        }
        @Override
        public void visitMaxs(int maxStack, int maxLocal) {
            mv.visitMaxs(maxStack+4, maxLocal);
        }
    }
    
}