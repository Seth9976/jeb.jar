package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvalSandboxExecutionException;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDSandboxHooks;
import com.pnfsoftware.jeb.util.base.Wrapper;
import java.util.List;

public class bvu extends bvn implements IDSandboxHooks {
   @Override
   public Class loadClass(String var1) throws DexDecEvalSandboxExecutionException {
      for (IDSandboxHooks var3 : this.A()) {
         Class var4 = var3.loadClass(var1);
         if (var4 != null) {
            return var4;
         }
      }

      return null;
   }

   @Override
   public Boolean setField(long var1, String var3, String var4, Object var5, Object[] var6) throws DexDecEvalSandboxExecutionException {
      for (IDSandboxHooks var8 : this.A()) {
         Boolean var9 = var8.setField(var1, var3, var4, var5, var6);
         if (var9 != null) {
            return var9;
         }
      }

      return false;
   }

   @Override
   public Wrapper getField(long var1, String var3, String var4, Object var5) throws DexDecEvalSandboxExecutionException {
      for (IDSandboxHooks var7 : this.A()) {
         Wrapper var8 = var7.getField(var1, var3, var4, var5);
         if (var8 != null) {
            return var8;
         }
      }

      return null;
   }

   @Override
   public Wrapper examineFieldValue(long var1, Object var3) {
      for (IDSandboxHooks var5 : this.A()) {
         Wrapper var6 = var5.examineFieldValue(var1, var3);
         if (var6 != null) {
            return var6;
         }
      }

      return null;
   }

   @Override
   public Wrapper invokeMethod(long var1, String var3, String var4, Object var5, List var6) throws DexDecEvalSandboxExecutionException {
      for (IDSandboxHooks var8 : this.A()) {
         Wrapper var9 = var8.invokeMethod(var1, var3, var4, var5, var6);
         if (var9 != null) {
            return var9;
         }
      }

      return null;
   }

   @Override
   public Wrapper examineMethodResult(long var1, Object var3) {
      for (IDSandboxHooks var5 : this.A()) {
         Wrapper var6 = var5.examineMethodResult(var1, var3);
         if (var6 != null) {
            return var6;
         }
      }

      return null;
   }

   @Override
   public Wrapper newInstance(long var1, String var3, String var4, List var5) throws DexDecEvalSandboxExecutionException {
      for (IDSandboxHooks var7 : this.A()) {
         Wrapper var8 = var7.newInstance(var1, var3, var4, var5);
         if (var8 != null) {
            return var8;
         }
      }

      return null;
   }

   @Override
   public void examineCreatedInstance(long var1, Object var3) {
      for (IDSandboxHooks var5 : this.A()) {
         var5.examineCreatedInstance(var1, var3);
      }
   }
}
