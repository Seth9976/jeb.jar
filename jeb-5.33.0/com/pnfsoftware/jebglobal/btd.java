package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.JvmUtil;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexMethod;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexType;
import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvalUnmanagedCodeException;
import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvaluationException;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.DefaultCall;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.Super;
import net.bytebuddy.implementation.bind.annotation.SuperCall;
import net.bytebuddy.implementation.bind.annotation.This;

public class btd {
   private static final ILogger A = GlobalLog.getLogger(btd.class);
   btp pC;

   btd(btp var1) {
      this.pC = var1;
   }

   @RuntimeType
   public Object pC(@This Object var1, @AllArguments Object[] var2, @Origin Method var3, @DefaultCall Callable var4) throws Exception {
      return this.pC(var1, null, var2, var3, var4, null);
   }

   @RuntimeType
   public Object pC(@This Object var1, @Super Object var2, @AllArguments Object[] var3, @Origin Method var4, @SuperCall Callable var5) throws Exception {
      return this.pC(var1, var2, var3, var4, null, var5);
   }

   @RuntimeType
   public Object pC(@This Object var1, @Super Object var2, @AllArguments Object[] var3, @Origin Method var4) throws Exception {
      return this.pC(var1, var2, var3, var4, null, null);
   }

   @RuntimeType
   public Object pC(@This Object var1, @AllArguments Object[] var2, @Origin Method var3) throws Exception {
      return this.pC(var1, null, var2, var3, null, null);
   }

   private Object pC(Object var1, Object var2, Object[] var3, Method var4, Callable var5, Callable var6) throws Exception {
      try {
         btp.pC(3, "INTERCEPTED: %s", var4);
         btk var7 = this.pC.A(var1);
         if (var7 != null) {
            int var8 = bvo.wS(var1);
            if (var8 == 0) {
               btp.pC(3, "  >> EMULATED OBJECT: %s", var7);
               if ((var4.getModifiers() & 26) == 0) {
                  String var9 = JvmUtil.generateMethodSig(var4);
                  String var10 = var7.pC(var9);
                  if (var10 != null) {
                     IDexMethod var11 = this.pC.getDex().getMethod(var10);
                     if (var11 != null && var11.isInternal()) {
                        ArrayList var12 = new ArrayList();
                        var12.add(this.pC.pC("L", var7));
                        int var13 = 0;

                        for (Object var17 : var3) {
                           String var18 = ((IDexType)var11.getParameterTypes().get(var13)).getSignature(false);
                           var12.add(this.pC.pC(var18, var17));
                           var13++;
                        }

                        IDImm var20 = this.pC.pC(var11, var12);
                        return this.pC.pC(var11.getReturnType().getSignature(false), var20);
                     }
                  }
               }
            }
         }

         if (var4.getDeclaringClass().getName().equals("java.lang.Thread")) {
            return this.pC(var4, (Thread)var1, var3);
         } else if (var6 != null) {
            return var6.call();
         } else {
            throw new RuntimeException("zzz");
         }
      } catch (Exception var19) {
         if (bvo.kS(Thread.currentThread())) {
            throw var19;
         } else {
            throw new DexDecEvalUnmanagedCodeException(var19);
         }
      }
   }

   Object pC(Method var1, Thread var2, Object[] var3) throws DexDecEvaluationException {
      btk var4 = this.pC.A((Object)var2);
      String var5 = JvmUtil.generateMethodSig(var1);
      switch (var5) {
         case "Ljava/lang/Thread;->start()V":
            String var8 = "Ljava/lang/Thread;->run()V";
            String var9 = var4.pC(var8);
            if (var9 != null && !var9.equals(var8)) {
               IDexMethod var10 = this.pC.getDex().getMethod(var9);
               Assert.a(var10 != null && var10.isInternal());
               ArrayList var11 = new ArrayList();
               var11.add(this.pC.pC("L", var4));
               this.pC.pC(var10, var11);
               return null;
            } else {
               throw new RuntimeException("TBI");
            }
         case "Ljava/lang/Thread;->join()V":
         case "Ljava/lang/Thread;->join(J)V":
         case "Ljava/lang/Thread;->join(JI)V":
            return null;
         case "Ljava/lang/Thread;->toString()Ljava/lang/String;":
            return "THREAD";
         default:
            throw new RuntimeException("THREAD TBI: " + var1);
      }
   }
}
