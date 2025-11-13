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

public class awk implements IManglingEngine {
   private static final ILogger pC = GlobalLog.getLogger(awk.class);
   private boolean A;

   @Override
   public IUnmangledData unmangle(String var1) {
      try {
         return this.pC(var1);
      } catch (Exception var3) {
         if (!this.A) {
            this.A = true;
            JebCoreService.notifySilentExceptionToClient(new UnmanglerException(Strings.ff("CXXA - on:%s", var1)));
         }

         return null;
      }
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   private IUnmangledData pC(String var1) {
      avv var2 = new avv();
      if (!var1.startsWith("_Z")) {
         if (!var1.startsWith("__Z")) {
            return null;
         }

         var1 = var1.substring(1);
      }

      avz var3 = new avz(var1);
      var3.gp = 1;
      awe var4 = var2.pC(var3, true);
      if (var4 == null) {
         return null;
      } else if (var3.pC() != 0) {
         return null;
      } else if (this.pC(var4)) {
         return null;
      } else {
         avx var5 = new avx();
         boolean var6 = false;
         HashSet var7 = new HashSet();
         awe var8 = null;
         awe var9 = null;
         awe var10 = null;
         awe var11 = null;
         String var12 = "";
         if (var4.pC == awg.Sc || var4.pC == awg.xC || var4.pC == awg.ED) {
            awa var13 = new awa();
            var5.pC(var13, var4);
            if (var13.wS) {
               return null;
            }

            int var14 = var13.pC.indexOf("to ");
            if (var14 < 0) {
               return null;
            }

            var12 = var13.pC.substring(0, var14 + 3).toString();
            var4 = var4.pC();
         }

         awe var23 = var4;

         while (var23.pC == awg.Br || var23.pC == awg.Um) {
            var23 = var23.pC();
         }

         if (var4.pC == awg.wS || var23.pC == awg.wS) {
            var6 = true;
            var8 = var4.pC();
            List var24 = this.pC(var8, var7);
            var8 = (awe)var24.get(0);
            if (var8 == null) {
               return null;
            }

            var8.UT = true;
            if (var24.size() > 1) {
               var9 = (awe)var24.get(1);
               var9.UT = true;
            }

            awe.Sv var15;
            if (var4.pC != awg.Br && var4.pC != awg.Um) {
               var15 = var4.A().wS.oT();
            } else {
               var15 = var23.wS.oT();
            }

            var10 = var15.pC;
            if (var10 != null) {
               var10.UT = true;
            }

            var11 = var15.A;
            if (var11 != null) {
               var11.UT = true;
            }
         }

         awa var25 = new awa();
         var5.pC(var25, var4);
         if (var25.wS) {
            return null;
         } else {
            Object var26;
            if (var6) {
               String var16 = var12 + var8.E + (var9 == null ? "" : "::" + var9.E);
               String var17 = null;
               if (var10 != null && var10.E != null && !var10.E.isEmpty()) {
                  var17 = var10.E;
               }

               List var18;
               if (var11 != null && var11.E != null && !var11.E.isEmpty()) {
                  var18 = TypeUtil.splitCppParameters(var11.E);
               } else {
                  var18 = Collections.emptyList();
               }

               ArrayList var19 = null;
               if (!var7.isEmpty()) {
                  for (awe var21 : var7) {
                     switch (var21.pC) {
                        case FE:
                        case LM:
                           if (var19 == null) {
                              var19 = new ArrayList();
                           }

                           var19.add("const");
                           break;
                        case Er:
                        case EX:
                           if (var19 == null) {
                              var19 = new ArrayList();
                           }

                           var19.add("volatile");
                     }
                  }
               }

               var26 = new avs(var16, var17, var18, var19, var12 + var25.pC.toString());
            } else {
               var26 = new avt(var12 + var25.pC.toString());
            }

            return (IUnmangledData)var26;
         }
      }
   }

   private boolean pC(awe var1) {
      ArrayList var2 = new ArrayList();
      var2.add(var1);
      int var3 = 0;

      while (!var2.isEmpty()) {
         var1 = (awe)var2.remove(0);
         if (var1 != null) {
            if (++var3 > 5000) {
               return true;
            }

            switch (var1.pC) {
               case FE:
               case LM:
               case Er:
               case EX:
               case tH:
               case A:
               case kS:
               case wS:
               case UT:
               case gp:
               case oT:
               case fI:
               case WR:
               case NS:
               case vP:
               case xC:
               case ED:
               case Sc:
               case ah:
               case eP:
               case rl:
               case z:
               case Um:
               case Ta:
               case hK:
               case Aj:
               case mv:
               case sO:
               case Gm:
               case Cu:
               case hZ:
               case UW:
               case PR:
               case cX:
               case DQ:
               case OB:
               case pF:
               case Bc:
               case OI:
               case ck:
               case RW:
               case xM:
               case go:
               case JF:
               case Nq:
               case pg:
               case gj:
               case ZD:
               case DL:
               case UH:
               case VD:
               case Xs:
               case KV:
               case Bi:
               case wQ:
               case Fm:
               case So:
               case UO:
               case Ab:
                  awe var4 = var1.pC();
                  if (var4 != null) {
                     var2.add(var4);
                  }

                  awe var5 = var1.A();
                  if (var5 != null) {
                     var2.add(var5);
                  }
               case pC:
               case E:
               case sY:
               case Ek:
               case ZN:
               case kU:
               case PZ:
               case Ip:
               default:
                  break;
               case pt:
               case gy:
                  awe var6 = var1.wS.fI().pC;
                  if (var6 != null) {
                     var2.add(var6);
                  }
                  break;
               case Bf:
                  awe var7 = var1.wS.WR().pC;
                  if (var7 != null) {
                     var2.add(var7);
                  }
                  break;
               case ys:
               case ld:
               case Kq:
                  awe var8 = ((awe.qt)var1.wS).A;
                  if (var8 != null) {
                     var2.add(var8);
                  }
                  break;
               case Gu:
                  awe var9 = var1.wS.sY().A.A;
                  if (var9 != null) {
                     var2.add(var9);
                  }
            }
         }
      }

      return false;
   }

   private List pC(awe var1, Set var2) {
      var1 = this.A(var1, var2);
      if (var1 != null && var1.pC == awg.kS) {
         awe var3 = var1.A();
         var1 = var1.pC();
         if (var1.pC == awg.wS || var1.pC == awg.Br) {
            return Arrays.asList((awe)this.pC(var1.pC(), var2).get(0), this.A(var3, var2));
         }
      } else if (var1.pC == awg.wS || var1.pC == awg.Br) {
         return Arrays.asList((awe)this.pC(var1.pC(), var2).get(0));
      }

      return Arrays.asList(var1);
   }

   private awe A(awe var1, Set var2) {
      while (var1 != null && avv.pC(var1.pC)) {
         var2.add(var1);
         var1 = var1.pC();
      }

      return var1;
   }
}
