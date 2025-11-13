package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.units.code.android.ContextAccessType;
import com.pnfsoftware.jeb.core.units.code.android.EffectiveFinalityType;
import com.pnfsoftware.jeb.core.units.code.android.IDexContextInfoProvider;
import com.pnfsoftware.jeb.core.units.code.android.JvmMethodSig;
import com.pnfsoftware.jeb.core.units.code.android.JvmTypeSig;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class bks implements IDexContextInfoProvider {
   private static final ILogger q = GlobalLog.getLogger(bks.class);
   private static final ContextAccessType RF = ContextAccessType.NONE_SAFE;
   private Map xK = new ConcurrentHashMap();
   private Map Dw = new ConcurrentHashMap();
   private Map Uv = new ConcurrentHashMap();
   private Map oW = new ConcurrentHashMap();
   private Map gO = new ConcurrentHashMap();
   private Map nf = new ConcurrentHashMap();

   public bks() {
      this(null);
   }

   public bks(String var1) {
      if (!Strings.isBlank(var1)) {
         for (String var5 : Strings.splitLines(var1)) {
            var5 = var5.trim();
            if (!var5.isEmpty() && !var5.startsWith("#")) {
               ContextAccessType var6 = RF;
               int var7 = var5.indexOf(61);
               if (var7 >= 0) {
                  String var8 = var5.substring(var7 + 1).trim();
                  var5 = var5.substring(0, var7).trim();

                  try {
                     var6 = ContextAccessType.valueOf(var8);
                  } catch (Exception var11) {
                     q.warn(
                        S.L("Illegal value for %s: %s (allowed: %s; default: %s)"),
                        ContextAccessType.class.getSimpleName(),
                        var8,
                        Arrays.toString((Object[])ContextAccessType.values()),
                        RF
                     );
                     continue;
                  }
               }

               if (var5.startsWith("*")) {
                  if (!var5.startsWith("*->")) {
                     q.warn(S.L("side-effect db: Illegal wildcard: %s"), var5);
                  } else {
                     this.Uv.put(var5.substring(3), var6);
                  }
               } else if (var5.contains("->")) {
                  try {
                     JvmMethodSig.parse(var5);
                     this.xK.put(var5, var6);
                  } catch (Exception var10) {
                     q.warn(S.L("side-effect db: Illegal method name: %s"), var5);
                  }
               } else {
                  try {
                     JvmTypeSig.verifyType(var5);
                     this.Dw.put(var5, var6);
                  } catch (Exception var9) {
                     q.warn(S.L("side-effect db: Illegal type name: %s"), var5);
                  }
               }
            }
         }
      }
   }

   @Override
   public boolean setMethodCAT(String var1, ContextAccessType var2) {
      return var2 == null ? this.xK.remove(var1) != null : this.xK.put(var1, var2) != var2;
   }

   @Override
   public ContextAccessType getMethodCAT(String var1) {
      if (var1 != null) {
         ContextAccessType var2 = (ContextAccessType)this.xK.get(var1);
         if (var2 != null) {
            return var2;
         }

         int var3 = var1.indexOf("->");
         if (var3 >= 0) {
            String var4 = var1.substring(0, var3);
            String var5 = var1.substring(var3 + 2);
            var2 = (ContextAccessType)this.Dw.get(var4);
            if (var2 != null) {
               if (!var5.startsWith("<clinit>(")
                  && !var5.startsWith("<init>(")
                  && !var5.startsWith("clone()")
                  && !var5.startsWith("notify()")
                  && !var5.startsWith("notifyAll()")
                  && !var5.startsWith("wait()")
                  && !var5.startsWith("wait(J)")
                  && !var5.startsWith("wait(JI)")) {
                  return var2;
               }

               return ContextAccessType.UNKNOWN;
            }

            var2 = (ContextAccessType)this.Uv.get(var5);
            if (var2 != null) {
               return var2;
            }
         }
      }

      return ContextAccessType.UNKNOWN;
   }

   @Override
   public boolean setFieldEFInfo(String var1, EffectiveFinalityType var2) {
      return var2 == null ? this.oW.remove(var1) != null : this.oW.put(var1, var2) != var2;
   }

   @Override
   public EffectiveFinalityType getFieldEFInfo(String var1) {
      if (var1 != null) {
         EffectiveFinalityType var2 = (EffectiveFinalityType)this.oW.get(var1);
         if (var2 != null) {
            return var2;
         }

         int var3 = var1.indexOf("->");
         if (var3 >= 0) {
            String var4 = var1.substring(0, var3);
            String var5 = var1.substring(var3 + 2);
            var2 = (EffectiveFinalityType)this.gO.get(var4);
            if (var2 != null) {
               return var2;
            }

            var2 = (EffectiveFinalityType)this.nf.get(var5);
            if (var2 != null) {
               return var2;
            }
         }
      }

      return EffectiveFinalityType.UNKNOWN;
   }
}
