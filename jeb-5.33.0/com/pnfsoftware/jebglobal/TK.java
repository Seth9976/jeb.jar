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

public class TK {
   private static final ILogger UT = GlobalLog.getLogger(TK.class);
   Map pC = new HashMap();
   Map A = new HashMap();
   Map kS = new HashMap();
   bA wS;

   TK(bA var1) {
      this.wS = var1;
   }

   public void pC() {
      this.pC.clear();
      this.A.clear();
      this.kS.clear();
   }

   public ITypedValue pC(com.pnfsoftware.jeb.corei.debuggers.android.vm.Tb var1, long var2, long var4) throws IOException, wX {
      String var6 = Strings.ff("%d:%d", var2, var4);
      ITypedValue var7 = (ITypedValue)this.kS.get(var6);
      if (var7 != null) {
         return var7;
      } else {
         bA var8 = (bA)var1.UT();

         try {
            uG var9 = var8.gp().A(var2, var4);
            if (var9 != null && var9.A != 0L) {
               sK var10 = this.A(var9.A);
               if (var10.pC == 1) {
                  var7 = var8.fI().pC(76, var1, var9.A, var10.A);
                  this.kS.put(var6, var7);
               }

               return var7;
            } else {
               return null;
            }
         } catch (oY var11) {
            throw new wX(var11, "Error when trying to retrieve this object of thread %d in frame %d", var2, var4);
         }
      }
   }

   public ITypedValue pC(int var1, com.pnfsoftware.jeb.corei.debuggers.android.vm.Tb var2, long var3, long var5) throws IOException, wX {
      Object var7 = (ITypedValue)this.pC.get(var3);
      if (var7 == null) {
         switch (var1) {
            case 76:
            default:
               if (var5 > 0L) {
                  String var12 = this.wS.oT().kS(var5);
                  if (var12.startsWith("[")) {
                     var7 = new com.pnfsoftware.jeb.corei.debuggers.android.vm.io(var2, var3, var5);
                  } else if (var12.equals("Ljava/lang/String;")) {
                     try {
                        String var9 = this.wS.gp().sY(var3);
                        var7 = new ValueString(var9, var3);
                     } catch (oY var10) {
                        UT.catching(var10);
                        return null;
                     }
                  } else {
                     var7 = new com.pnfsoftware.jeb.corei.debuggers.android.vm.gb(var2, var3, var5, "object");
                  }
               }
               break;
            case 91:
               var7 = new com.pnfsoftware.jeb.corei.debuggers.android.vm.io(var2, var3, var5);
               break;
            case 115:
               try {
                  String var8 = this.wS.gp().sY(var3);
                  var7 = new ValueString(var8, var3);
               } catch (oY var11) {
                  UT.catching(var11);
                  return null;
               }
         }

         this.pC.put(var3, var7);
      }

      return (ITypedValue)var7;
   }

   public ITypedValue pC(com.pnfsoftware.jeb.corei.debuggers.android.vm.Tb var1, long var2, long var4, String var6) {
      if (var2 == 0L) {
         Object var8 = (ITypedValue)this.A.get(var6);
         if (var8 == null) {
            if ("Ljava/lang/String;".equals(var6)) {
               var8 = new ValueString(null, var2);
            } else {
               var8 = new com.pnfsoftware.jeb.corei.debuggers.android.vm.gb(var1, var2, var4, var6);
            }

            if (var4 == 0L) {
               this.A.put(var6, var8);
            }
         }

         return (ITypedValue)var8;
      } else {
         Object var7 = (ITypedValue)this.pC.get(var2);
         if (var7 == null) {
            var7 = new com.pnfsoftware.jeb.corei.debuggers.android.vm.gb(var1, var2, var4, var6);
            this.pC.put(var2, var7);
         }

         return (ITypedValue)var7;
      }
   }

   public IDebuggerVariable pC(com.pnfsoftware.jeb.corei.debuggers.android.vm.Tb var1, String var2, IDebuggerVariable var3) throws wX {
      long var4 = Conversion.stringToLong(var2);
      if (var4 != 0L) {
         bA var6 = (bA)var1.UT();

         try {
            sK var7 = var6.gp().E(var4);
            if (var7.pC == 1) {
               ITypedValue var8 = this.pC(76, var1, var4, var7.A);
               return new com.pnfsoftware.jeb.corei.debuggers.android.vm.Kr("", var8, 0, var1, null);
            }

            return null;
         } catch (oY | IOException var9) {
            UT.catching(var9);
         }
      }

      return var3 != null && !(var3.getTypedValue() instanceof AbstractValuePrimitive) ? this.pC(var3, pC(var2)) : null;
   }

   public static List pC(String var0) {
      ArrayList var1 = new ArrayList();
      pC(var0, var1);
      return var1;
   }

   private static void pC(String var0, List var1) {
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
         var1.add(new TK.Av(false, var3));
         if (var2 + 1 < var0.length()) {
            pC(var0.substring(var2 + 1), var1);
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
         var1.add(new TK.Av(true, var5));
         if (var4 != -1) {
            pC(var0.substring(var4), var1);
         }
      }
   }

   private IDebuggerVariable pC(IDebuggerVariable var1, List var2) throws wX {
      for (TK.Av var4 : var2) {
         ITypedValue var5 = var1.getTypedValue();
         if (!var4.pC || !(var5 instanceof ValueObject)) {
            if (var4.pC || !(var5 instanceof ValueArray)) {
               throw new wX(Strings.ff("Field %s is not reachable", var4.A));
            }

            var1 = (IDebuggerVariable)((ValueArray)var5).getElements(Integer.parseInt(var4.A), 1).get(0);
         } else {
            List var6 = ((ValueObject)var5).getValue();
            boolean var7 = false;
            Iterator var8 = var6.iterator();

            while (true) {
               if (var8.hasNext()) {
                  IDebuggerVariable var9 = (IDebuggerVariable)var8.next();
                  if (!var9.getName().equals(var4.A)) {
                     continue;
                  }

                  var1 = var9;
                  var7 = true;
               }

               if (!var7) {
                  throw new wX(Strings.ff("Field %s is not reachable", var4.A));
               }
               break;
            }
         }
      }

      return var1;
   }

   private sK A(long var1) throws IOException, wX {
      try {
         return this.wS.gp().E(var1);
      } catch (oY var4) {
         throw new wX(var4, "Error while trying to retrieve class id from object %d", var1);
      }
   }

   public long pC(long var1) throws IOException, wX {
      return this.A(var1).A;
   }

   public static class Av {
      boolean pC;
      String A;

      public Av(boolean var1, String var2) {
         this.pC = var1;
         this.A = var2;
      }
   }
}
