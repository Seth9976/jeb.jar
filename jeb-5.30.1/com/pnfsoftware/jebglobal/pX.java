package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.debug.IDebuggerVariable;
import com.pnfsoftware.jeb.core.units.code.debug.ITypedValue;
import com.pnfsoftware.jeb.core.units.code.debug.impl.AbstractValuePrimitive;
import com.pnfsoftware.jeb.core.units.code.debug.impl.ValueArray;
import com.pnfsoftware.jeb.core.units.code.debug.impl.ValueObject;
import com.pnfsoftware.jeb.core.units.code.debug.impl.ValueString;
import com.pnfsoftware.jeb.util.encoding.Conversion;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class pX {
   private static final ILogger Uv = GlobalLog.getLogger(pX.class);
   Map q = new HashMap();
   Map RF = new HashMap();
   Map xK = new HashMap();
   Ux Dw;

   pX(Ux var1) {
      this.Dw = var1;
   }

   public void q() {
      this.q.clear();
      this.RF.clear();
      this.xK.clear();
   }

   public ITypedValue q(com.pnfsoftware.jeb.corei.debuggers.android.vm.CI var1, long var2, long var4) throws IOException, zy {
      String var6 = Strings.ff("%d:%d", var2, var4);
      ITypedValue var7 = (ITypedValue)this.xK.get(var6);
      if (var7 != null) {
         return var7;
      } else {
         Ux var8 = (Ux)var1.oW();

         try {
            VO var9 = var8.zz().xK(var2, var4);
            if (var9 != null && var9.RF != 0L) {
               gg var10 = this.RF(var9.RF);
               if (var10.q == 1) {
                  var7 = var8.HF().q(76, var1, var9.RF, var10.RF);
                  this.xK.put(var6, var7);
               }

               return var7;
            } else {
               return null;
            }
         } catch (Fx var11) {
            throw new zy(var11, "Error when trying to retrieve this object of thread %d in frame %d", var2, var4);
         }
      }
   }

   public ITypedValue q(int var1, com.pnfsoftware.jeb.corei.debuggers.android.vm.CI var2, long var3, long var5) throws IOException, zy {
      Object var7 = (ITypedValue)this.q.get(var3);
      if (var7 == null) {
         switch (var1) {
            case 76:
            default:
               if (var5 > 0L) {
                  String var12 = this.Dw.JY().oW(var5);
                  if (var12.startsWith("[")) {
                     var7 = new com.pnfsoftware.jeb.corei.debuggers.android.vm.eM(var2, var3, var5);
                  } else if (var12.equals("Ljava/lang/String;")) {
                     try {
                        String var9 = this.Dw.zz().gP(var3);
                        var7 = new ValueString(var9, var3);
                     } catch (Fx var10) {
                        Uv.catching(var10);
                        return null;
                     }
                  } else {
                     var7 = new com.pnfsoftware.jeb.corei.debuggers.android.vm.HA(var2, var3, var5, "object");
                  }
               }
               break;
            case 91:
               var7 = new com.pnfsoftware.jeb.corei.debuggers.android.vm.eM(var2, var3, var5);
               break;
            case 115:
               try {
                  String var8 = this.Dw.zz().gP(var3);
                  var7 = new ValueString(var8, var3);
               } catch (Fx var11) {
                  Uv.catching(var11);
                  return null;
               }
         }

         this.q.put(var3, var7);
      }

      return (ITypedValue)var7;
   }

   public ITypedValue q(com.pnfsoftware.jeb.corei.debuggers.android.vm.CI var1, long var2, long var4, String var6) {
      if (var2 == 0L) {
         Object var8 = (ITypedValue)this.RF.get(var6);
         if (var8 == null) {
            if ("Ljava/lang/String;".equals(var6)) {
               var8 = new ValueString(null, var2);
            } else {
               var8 = new com.pnfsoftware.jeb.corei.debuggers.android.vm.HA(var1, var2, var4, var6);
            }

            if (var4 == 0L) {
               this.RF.put(var6, var8);
            }
         }

         return (ITypedValue)var8;
      } else {
         Object var7 = (ITypedValue)this.q.get(var2);
         if (var7 == null) {
            var7 = new com.pnfsoftware.jeb.corei.debuggers.android.vm.HA(var1, var2, var4, var6);
            this.q.put(var2, var7);
         }

         return (ITypedValue)var7;
      }
   }

   public IDebuggerVariable q(com.pnfsoftware.jeb.corei.debuggers.android.vm.CI var1, String var2, IDebuggerVariable var3) throws zy {
      long var4 = Conversion.stringToLong(var2);
      if (var4 != 0L) {
         Ux var6 = (Ux)var1.oW();

         try {
            gg var7 = var6.zz().gO(var4);
            if (var7.q == 1) {
               ITypedValue var8 = this.q(76, var1, var4, var7.RF);
               return new com.pnfsoftware.jeb.corei.debuggers.android.vm.LR("", var8, 0, var1, null);
            }

            return null;
         } catch (Fx | IOException var9) {
            Uv.catching(var9);
         }
      }

      return var3 != null && !(var3.getTypedValue() instanceof AbstractValuePrimitive) ? this.q(var3, q(var2)) : null;
   }

   public static List q(String var0) {
      ArrayList var1 = new ArrayList();
      q(var0, var1);
      return var1;
   }

   private static void q(String var0, List var1) {
      var0 = var0.trim();
      if (var0.startsWith(".")) {
         var0 = var0.substring(1);
      }

      if (var0.startsWith("[")) {
         int var2 = var0.indexOf("]");
         if (var2 == -1) {
            throw new IllegalArgumentException("Array argument not closed");
         }

         String var3 = var0.substring(1, var2);
         var1.add(new pX.eo(false, var3));
         if (var2 + 1 < var0.length()) {
            q(var0.substring(var2 + 1), var1);
         }
      } else {
         int var7 = var0.indexOf(46);
         int var8 = var0.indexOf(91);
         int var4;
         if (var7 == -1) {
            if (var8 == -1) {
               var4 = -1;
            } else {
               var4 = var8;
            }
         } else if (var8 == -1) {
            var4 = var7;
         } else {
            var4 = Math.min(var8, var7);
         }

         String var5 = var4 == -1 ? var0 : var0.substring(0, var4);
         var1.add(new pX.eo(true, var5));
         if (var4 != -1) {
            q(var0.substring(var4), var1);
         }
      }
   }

   private IDebuggerVariable q(IDebuggerVariable var1, List var2) throws zy {
      for (pX.eo var4 : var2) {
         ITypedValue var5 = var1.getTypedValue();
         if (!var4.q || !(var5 instanceof ValueObject)) {
            if (var4.q || !(var5 instanceof ValueArray)) {
               throw new zy(Strings.ff("Field %s is not reachable", var4.RF));
            }

            var1 = (IDebuggerVariable)((ValueArray)var5).getElements(Integer.parseInt(var4.RF), 1).get(0);
         } else {
            List var6 = ((ValueObject)var5).getValue();
            boolean var7 = false;
            Iterator var8 = var6.iterator();

            while (true) {
               if (var8.hasNext()) {
                  IDebuggerVariable var9 = (IDebuggerVariable)var8.next();
                  if (!var9.getName().equals(var4.RF)) {
                     continue;
                  }

                  var1 = var9;
                  var7 = true;
               }

               if (!var7) {
                  throw new zy(Strings.ff("Field %s is not reachable", var4.RF));
               }
               break;
            }
         }
      }

      return var1;
   }

   private gg RF(long var1) throws IOException, zy {
      try {
         return this.Dw.zz().gO(var1);
      } catch (Fx var4) {
         throw new zy(var4, "Error while trying to retrieve class id from object %d", var1);
      }
   }

   public long q(long var1) throws IOException, zy {
      return this.RF(var1).RF;
   }

   public static class eo {
      boolean q;
      String RF;

      public eo(boolean var1, String var2) {
         this.q = var1;
         this.RF = var2;
      }

      public boolean q() {
         return this.q;
      }

      public String RF() {
         return this.RF;
      }
   }
}
