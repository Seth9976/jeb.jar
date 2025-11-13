package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.IDEmulatorHooks;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.util.base.Wrapper;
import java.util.List;

public class btl extends bvn implements IDEmulatorHooks {
   @Override
   public Wrapper getField(long var1, String var3, IDImm var4) {
      for (IDEmulatorHooks var6 : this.A()) {
         Wrapper var7 = var6.getField(var1, var3, var4);
         if (var7 != null) {
            return var7;
         }
      }

      return null;
   }

   @Override
   public Wrapper examineFieldValue(long var1, Wrapper var3) {
      for (IDEmulatorHooks var5 : this.A()) {
         Wrapper var6 = var5.examineFieldValue(var1, var3);
         if (var6 != null) {
            return var6;
         }
      }

      return null;
   }

   @Override
   public Boolean setField(long var1, String var3, IDImm var4, IDImm[] var5) {
      for (IDEmulatorHooks var7 : this.A()) {
         Boolean var8 = var7.setField(var1, var3, var4, var5);
         if (var8 != null) {
            return var8;
         }
      }

      return null;
   }

   @Override
   public Wrapper invokeMethod(long var1, String var3, List var4) {
      for (IDEmulatorHooks var6 : this.A()) {
         Wrapper var7 = var6.invokeMethod(var1, var3, var4);
         if (var7 != null) {
            return var7;
         }
      }

      return null;
   }

   @Override
   public Wrapper examineMethodResult(long var1, IDImm var3) {
      for (IDEmulatorHooks var5 : this.A()) {
         Wrapper var6 = var5.examineMethodResult(var1, var3);
         if (var6 != null) {
            return var6;
         }
      }

      return null;
   }
}
