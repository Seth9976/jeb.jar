package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.exceptions.UnmanglerException;
import com.pnfsoftware.jeb.core.units.code.asm.mangling.IManglingEngine;
import com.pnfsoftware.jeb.core.units.code.asm.mangling.IUnmangledData;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeUtil;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class azh implements IManglingEngine {
   private static final ILogger q = GlobalLog.getLogger(azh.class);
   private boolean RF;

   @Override
   public IUnmangledData unmangle(String var1) {
      try {
         return this.q(var1);
      } catch (Exception var3) {
         if (!this.RF) {
            this.RF = true;
            JebCoreService.notifySilentExceptionToClient(new UnmanglerException(Strings.ff("CXXA - on:%s", var1)));
         }

         return null;
      }
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   private IUnmangledData q(String var1) {
      ays var2 = new ays();
      if (!var1.startsWith("_Z")) {
         if (!var1.startsWith("__Z")) {
            return null;
         }

         var1 = var1.substring(1);
      }

      ayw var3 = new ayw(var1);
      var3.lm = 1;
      azb var4 = var2.q(var3, true);
      if (var4 == null) {
         return null;
      } else if (var3.q() != 0) {
         return null;
      } else if (this.q(var4)) {
         return null;
      } else {
         ayu var5 = new ayu();
         boolean var6 = false;
         HashSet var7 = new HashSet();
         azb var8 = null;
         azb var9 = null;
         azb var10 = null;
         azb var11 = null;
         String var12 = "";
         if (var4.q == azd.Hk || var4.q == azd.io || var4.q == azd.qa) {
            ayx var13 = new ayx();
            var5.q(var13, var4);
            if (var13.Dw) {
               return null;
            }

            int var14 = var13.q.indexOf("to ");
            if (var14 < 0) {
               return null;
            }

            var12 = var13.q.substring(0, var14 + 3).toString();
            var4 = var4.q();
         }

         azb var23 = var4;

         while (var23.q == azd.Wp || var23.q == azd.Yw) {
            var23 = var23.q();
         }

         if (var4.q == azd.Dw || var23.q == azd.Dw) {
            var6 = true;
            var8 = var4.q();
            List var24 = this.q(var8, var7);
            var8 = (azb)var24.get(0);
            if (var8 == null) {
               return null;
            }

            var8.Uv = true;
            if (var24.size() > 1) {
               var9 = (azb)var24.get(1);
               var9.Uv = true;
            }

            azb.CU var15;
            if (var4.q != azd.Wp && var4.q != azd.Yw) {
               var15 = var4.RF().Dw.lm();
            } else {
               var15 = var23.Dw.lm();
            }

            var10 = var15.q;
            if (var10 != null) {
               var10.Uv = true;
            }

            var11 = var15.RF;
            if (var11 != null) {
               var11.Uv = true;
            }
         }

         ayx var25 = new ayx();
         var5.q(var25, var4);
         if (var25.Dw) {
            return null;
         } else {
            Object var26;
            if (var6) {
               String var16 = var12 + var8.oW + (var9 == null ? "" : "::" + var9.oW);
               String var17 = null;
               if (var10 != null && var10.oW != null && !var10.oW.isEmpty()) {
                  var17 = var10.oW;
               }

               List var18;
               if (var11 != null && var11.oW != null && !var11.oW.isEmpty()) {
                  var18 = TypeUtil.splitCppParameters(var11.oW);
               } else {
                  var18 = Collections.emptyList();
               }

               ArrayList var19 = null;
               if (!var7.isEmpty()) {
                  for (azb var21 : var7) {
                     switch (azi.q[var21.q.ordinal()]) {
                        case 1:
                        case 2:
                           if (var19 == null) {
                              var19 = new ArrayList();
                           }

                           var19.add("const");
                           break;
                        case 3:
                        case 4:
                           if (var19 == null) {
                              var19 = new ArrayList();
                           }

                           var19.add("volatile");
                     }
                  }
               }

               var26 = new ayp(var16, var17, var18, var19, var12 + var25.q.toString());
            } else {
               var26 = new ayq(var12 + var25.q.toString());
            }

            return (IUnmangledData)var26;
         }
      }
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   private boolean q(azb var1) {
      ArrayList var2 = new ArrayList();
      var2.add(var1);
      int var3 = 0;

      while (!var2.isEmpty()) {
         var1 = (azb)var2.remove(0);
         if (var1 != null) {
            if (++var3 > 5000) {
               return true;
            }

            switch (azi.q[var1.q.ordinal()]) {
               case 1:
               case 2:
               case 3:
               case 4:
               case 13:
               case 14:
               case 15:
               case 16:
               case 17:
               case 18:
               case 19:
               case 20:
               case 21:
               case 22:
               case 23:
               case 24:
               case 25:
               case 26:
               case 27:
               case 28:
               case 29:
               case 30:
               case 31:
               case 32:
               case 33:
               case 34:
               case 35:
               case 36:
               case 37:
               case 38:
               case 39:
               case 40:
               case 41:
               case 42:
               case 43:
               case 44:
               case 45:
               case 46:
               case 47:
               case 48:
               case 49:
               case 50:
               case 51:
               case 52:
               case 53:
               case 54:
               case 55:
               case 56:
               case 57:
               case 58:
               case 59:
               case 60:
               case 61:
               case 62:
               case 63:
               case 64:
               case 65:
               case 66:
               case 67:
                  azb var4 = var1.q();
                  if (var4 != null) {
                     var2.add(var4);
                  }

                  azb var5 = var1.RF();
                  if (var5 != null) {
                     var2.add(var5);
                  }
               case 5:
               case 6:
               case 7:
               case 8:
               case 9:
               case 10:
               case 11:
               case 12:
               default:
                  break;
               case 68:
               case 69:
                  azb var6 = var1.Dw.zz().q;
                  if (var6 != null) {
                     var2.add(var6);
                  }
                  break;
               case 70:
                  azb var7 = var1.Dw.JY().q;
                  if (var7 != null) {
                     var2.add(var7);
                  }
                  break;
               case 71:
               case 72:
               case 73:
                  azb var8 = ((azb.Vj)var1.Dw).RF;
                  if (var8 != null) {
                     var2.add(var8);
                  }
                  break;
               case 74:
                  azb var9 = var1.Dw.gO().RF.RF;
                  if (var9 != null) {
                     var2.add(var9);
                  }
            }
         }
      }

      return false;
   }

   private List q(azb var1, Set var2) {
      var1 = this.RF(var1, var2);
      if (var1 != null && var1.q == azd.xK) {
         azb var3 = var1.RF();
         var1 = var1.q();
         if (var1.q == azd.Dw || var1.q == azd.Wp) {
            return Arrays.asList((azb)this.q(var1.q(), var2).get(0), this.RF(var3, var2));
         }
      } else if (var1.q == azd.Dw || var1.q == azd.Wp) {
         return Arrays.asList((azb)this.q(var1.q(), var2).get(0));
      }

      return Arrays.asList(var1);
   }

   private azb RF(azb var1, Set var2) {
      while (var1 != null && ays.q(var1.q)) {
         var2.add(var1);
         var1 = var1.q();
      }

      return var1;
   }
}
