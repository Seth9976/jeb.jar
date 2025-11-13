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

public class bgw implements IDexContextInfoProvider {
   private static final ILogger pC = GlobalLog.getLogger(bgw.class);
   private static final ContextAccessType A = ContextAccessType.NONE_SAFE;
   private Map kS = new ConcurrentHashMap();
   private Map wS = new ConcurrentHashMap();
   private Map UT = new ConcurrentHashMap();
   private Map E = new ConcurrentHashMap();
   private Map sY = new ConcurrentHashMap();
   private Map ys = new ConcurrentHashMap();

   public bgw() {
      this(null);
   }

   public bgw(String var1) {
      if (!Strings.isBlank(var1)) {
         for (String var5 : Strings.splitLines(var1)) {
            var5 = var5.trim();
            if (!var5.isEmpty() && !var5.startsWith("#")) {
               ContextAccessType var6 = A;
               int var7 = var5.indexOf(61);
               if (var7 >= 0) {
                  String var8 = var5.substring(var7 + 1).trim();
                  var5 = var5.substring(0, var7).trim();

                  try {
                     var6 = ContextAccessType.valueOf(var8);
                  } catch (Exception var11) {
                     pC.warn(
                        S.L("Illegal value for %s: %s (allowed: %s; default: %s)"),
                        ContextAccessType.class.getSimpleName(),
                        var8,
                        Arrays.toString((Object[])ContextAccessType.values()),
                        A
                     );
                     continue;
                  }
               }

               if (var5.startsWith("*")) {
                  if (!var5.startsWith("*->")) {
                     pC.warn(S.L("side-effect db: Illegal wildcard: %s"), var5);
                  } else {
                     this.UT.put(var5.substring(3), var6);
                  }
               } else if (var5.contains("->")) {
                  try {
                     JvmMethodSig.parse(var5);
                     this.kS.put(var5, var6);
                  } catch (Exception var10) {
                     pC.warn(S.L("side-effect db: Illegal method name: %s"), var5);
                  }
               } else {
                  try {
                     JvmTypeSig.verifyType(var5);
                     this.wS.put(var5, var6);
                  } catch (Exception var9) {
                     pC.warn(S.L("side-effect db: Illegal type name: %s"), var5);
                  }
               }
            }
         }
      }
   }

   @Override
   public boolean setMethodCAT(String var1, ContextAccessType var2) {
      return var2 == null ? this.kS.remove(var1) != null : this.kS.put(var1, var2) != var2;
   }

   @Override
   public ContextAccessType getMethodCAT(String var1) {
      if (var1 != null) {
         ContextAccessType var2 = (ContextAccessType)this.kS.get(var1);
         if (var2 != null) {
            return var2;
         }

         int var3 = var1.indexOf("->");
         if (var3 >= 0) {
            String var4 = var1.substring(0, var3);
            String var5 = var1.substring(var3 + 2);
            var2 = (ContextAccessType)this.wS.get(var4);
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

            var2 = (ContextAccessType)this.UT.get(var5);
            if (var2 != null) {
               return var2;
            }
         }
      }

      return ContextAccessType.UNKNOWN;
   }

   @Override
   public boolean setFieldEFInfo(String var1, EffectiveFinalityType var2) {
      return var2 == null ? this.E.remove(var1) != null : this.E.put(var1, var2) != var2;
   }

   @Override
   public EffectiveFinalityType getFieldEFInfo(String var1) {
      if (var1 != null) {
         EffectiveFinalityType var2 = (EffectiveFinalityType)this.E.get(var1);
         if (var2 != null) {
            return var2;
         }

         int var3 = var1.indexOf("->");
         if (var3 >= 0) {
            String var4 = var1.substring(0, var3);
            String var5 = var1.substring(var3 + 2);
            var2 = (EffectiveFinalityType)this.sY.get(var4);
            if (var2 != null) {
               return var2;
            }

            var2 = (EffectiveFinalityType)this.ys.get(var5);
            if (var2 != null) {
               return var2;
            }
         }
      }

      return EffectiveFinalityType.UNKNOWN;
   }
}
