package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;

class avv {
   private static final ILogger pC = GlobalLog.getLogger(avv.class);
   private static final int A = "_GLOBAL_".length();

   static boolean pC(awg var0) {
      return var0 == awg.Aj
         || var0 == awg.EX
         || var0 == awg.LM
         || var0 == awg.mv
         || var0 == awg.sO
         || var0 == awg.os
         || var0 == awg.Gm
         || var0 == awg.AU
         || var0 == awg.jS;
   }

   private boolean pC(awe var1, String var2, int var3) {
      if (var1 != null && var2 != null && var3 > 0) {
         var1.A = 0;
         var1.kS = 0;
         var1.pC = awg.pC;
         var1.wS = new awe.KD();
         var1.wS.pC().pC = !var2.isEmpty() ? var2.substring(0, var3) : var2;
         return true;
      } else {
         return false;
      }
   }

   private boolean pC(awe var1, int var2, awe var3) {
      if (var1 != null && var2 >= 0 && var3 != null) {
         var1.A = 0;
         var1.kS = 0;
         var1.pC = awg.Kq;
         var1.wS = new awe.rQ();
         var1.wS.kS().pC = var2;
         var1.wS.kS().A = var3;
         return true;
      } else {
         return false;
      }
   }

   private boolean pC(awe var1, awe.oP var2, awe var3) {
      if (var1 != null && var3 != null) {
         var1.A = 0;
         var1.kS = 0;
         var1.pC = awg.ys;
         var1.wS = new awe.bO();
         var1.wS.wS().pC = var2;
         var1.wS.wS().A = var3;
         return true;
      } else {
         return false;
      }
   }

   private boolean pC(awe var1, awe.vi var2, awe var3) {
      if (var1 != null && var3 != null) {
         var1.A = 0;
         var1.kS = 0;
         var1.pC = awg.ld;
         var1.wS = new awe.cq();
         var1.wS.UT().pC = var2;
         var1.wS.UT().A = var3;
         return true;
      } else {
         return false;
      }
   }

   private awe pC(avz var1) {
      awe var2 = new awe();
      var2.A = 0;
      var2.kS = 0;
      var1.wS.add(var2);
      return var2;
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   private awe pC(avz var1, awg var2, awe var3, awe var4) {
      switch (var2) {
         case tH:
         case A:
         case kS:
         case wS:
         case UT:
         case fI:
         case Cu:
         case OI:
         case pg:
         case gj:
         case ZD:
         case DL:
         case UH:
         case Xs:
         case KV:
         case FK:
         case wQ:
         case Pe:
         case Br:
         case LL:
         case ii:
            if (var3 == null || var4 == null) {
               return null;
            }
            break;
         case E:
         case sY:
         case ys:
         case ld:
         case Ek:
         case ZN:
         case Gu:
         case kU:
         case Kq:
         case Bf:
         case PZ:
         case Ip:
         case pt:
         case gy:
         default:
            return null;
         case e:
         case gp:
         case oT:
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
         case hZ:
         case UW:
         case PR:
         case cX:
         case DQ:
         case OB:
         case go:
         case JF:
         case Nq:
         case VD:
         case Bi:
         case Fm:
         case So:
         case UO:
         case Ab:
         case FM:
         case Wn:
         case KK:
         case rC:
         case be:
         case sz:
         case QQ:
         case eE:
         case IE:
         case dM:
         case EM:
         case fD:
            if (var3 == null) {
               return null;
            }
         case hK:
         case Er:
         case FE:
         case Aj:
         case EX:
         case LM:
         case mv:
         case sO:
         case os:
         case Gm:
         case pF:
         case ck:
         case RW:
         case Xh:
         case AU:
         case jS:
            break;
         case Bc:
         case xM:
         case oB:
         case Rq:
            if (var4 == null) {
               return null;
            }
      }

      awe var5 = this.pC(var1);
      if (var5 != null) {
         var5.pC = var2;
         var5.wS = new awe.Sv();
         var5.wS.oT().pC = var3;
         var5.wS.oT().A = var4;
      }

      return var5;
   }

   private awe pC(avz var1, String var2, int var3) {
      awe var4 = this.pC(var1);
      return !this.pC(var4, var2, var3) ? null : var4;
   }

   private awe pC(avz var1, awd var2) {
      if (var2 == null) {
         return null;
      } else {
         awe var3 = this.pC(var1);
         if (var3 != null) {
            var3.pC = awg.ZN;
            var3.wS = new awe.K();
            var3.wS.E().pC = var2;
         }

         return var3;
      }
   }

   private awe pC(avz var1, awd var2, int var3, char var4) {
      return this.pC(var1, var2, new awc((short)var3), var4);
   }

   private awe pC(avz var1, awd var2, awc var3, char var4) {
      if (var2 == null) {
         return null;
      } else {
         awe var5 = this.pC(var1);
         if (var5 != null) {
            var5.pC = awg.Gu;
            var5.wS = new awe.DH();
            var5.wS.sY().pC = var2;
            var5.wS.sY().A = var3;
            var5.wS.sY().wS = var4;
         }

         return var5;
      }
   }

   private awe pC(avz var1, awh var2) {
      awe var3 = this.pC(var1);
      if (var3 != null) {
         var3.pC = awg.kU;
         var3.wS = new awe.RC();
         var3.wS.A().pC = var2;
      }

      return var3;
   }

   private awe pC(avz var1, int var2, awe var3) {
      awe var4 = this.pC(var1);
      return !this.pC(var4, var2, var3) ? null : var4;
   }

   private awe A(avz var1, int var2, awe var3) {
      awe var4 = this.pC(var1);
      if (var4 != null) {
         var4.pC = awg.pt;
         var4.wS = new awe.HE();
         var4.wS.fI().A = var2;
         var4.wS.fI().pC = var3;
      }

      return var4;
   }

   private awe pC(avz var1, awe.oP var2, awe var3) {
      awe var4 = this.pC(var1);
      return !this.pC(var4, var2, var3) ? null : var4;
   }

   private awe pC(avz var1, awe.vi var2, awe var3) {
      awe var4 = this.pC(var1);
      return !this.pC(var4, var2, var3) ? null : var4;
   }

   private awe pC(avz var1, int var2) {
      awe var3 = this.pC(var1);
      if (var3 != null) {
         var3.pC = awg.E;
         var3.wS = new awe.yt();
         var3.wS.ld().pC = var2;
      }

      return var3;
   }

   private awe A(avz var1, int var2) {
      awe var3 = this.pC(var1);
      if (var3 != null) {
         var3.pC = awg.sY;
         var3.wS = new awe.yt();
         var3.wS.ld().pC = var2;
      }

      return var3;
   }

   private awe pC(avz var1, String var2) {
      awe var3 = this.pC(var1);
      if (var3 != null) {
         var3.pC = awg.Ek;
         var3.wS = new awe.sy();
         var3.wS.ys().pC = var2;
      }

      return var3;
   }

   public awe pC(avz var1, boolean var2) {
      if (!var1.pC('_') && var2) {
         return null;
      } else if (!var1.pC('Z')) {
         return null;
      } else {
         awe var3 = this.A(var1, var2);
         if (var2) {
            while (var1.pC() == '.' && (Character.isLowerCase(var1.A()) || var1.A() == '_' || Character.isDigit(var1.A()))) {
               var3 = this.wS(var1, var3);
            }
         }

         return var3;
      }
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   private boolean A(awe var1) {
      if (var1 == null) {
         return false;
      } else {
         switch (var1.pC) {
            case kS:
               return this.A(var1.A());
            case UT:
               return !this.kS(var1.pC());
            case Aj:
            case EX:
            case LM:
            case mv:
            case sO:
            case os:
            case Gm:
            case AU:
            case jS:
               return this.A(var1.pC());
            default:
               return false;
         }
      }
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   private boolean kS(awe var1) {
      if (var1 == null) {
         return false;
      } else {
         switch (var1.pC) {
            case ys:
            case ld:
            case JF:
               return true;
            case A:
            case kS:
               return this.kS(var1.A());
            default:
               return false;
         }
      }
   }

   private awe pC(avz var1, awe var2) {
      if (var1.pC() == 'Q') {
         var1.pC(1);
         awe var3 = this.rl(var1);
         if (var3 == null) {
            return null;
         }

         var2 = this.pC(var1, awg.ii, var2, var3);
      }

      return var2;
   }

   private awe A(avz var1, boolean var2) {
      char var3 = var1.pC();
      if (var3 != 'G' && var3 != 'T') {
         awe var4 = this.kS(var1, false);
         var3 = var1.pC();
         if (var4 != null && var3 != 0 && var3 != 'E' && var3 != '.') {
            awe var5 = this.E(var1, this.A(var4));
            if (var5 == null) {
               return null;
            } else {
               if (!var2 && var4.pC == awg.kS && var5.pC == awg.pF) {
                  var5.wS.oT().pC = null;
               }

               var5 = this.pC(var1, var5);
               return this.pC(var1, awg.wS, var4, var5);
            }
         } else {
            return var4;
         }
      } else {
         return this.sY(var1);
      }
   }

   private awe A(avz var1, awe var2) {
      awe var3 = var1.E;
      char var4 = var1.pC();
      int var5 = 0;

      while (var4 == 'B') {
         var1.pC(1);
         awe var6 = this.kS(var1);
         var2 = this.pC(var1, awg.tH, var2, var6);
         var4 = var1.pC();
         if (++var5 > 1000) {
            return null;
         }
      }

      var1.E = var3;
      return var2;
   }

   private awe kS(avz var1, boolean var2) {
      char var3 = var1.pC();
      boolean var4 = false;
      awe var5 = null;
      awe var6 = null;
      switch (var3) {
         case 'L':
         case 'M':
         case 'O':
         case 'P':
         case 'Q':
         case 'R':
         case 'T':
         case 'V':
         case 'W':
         case 'X':
         case 'Y':
         default:
            var5 = this.pC(var1, var5, var6);
            if (var1.pC() == 'I') {
               var1.pC(var5);
               var5 = this.pC(var1, awg.UT, var5, this.Sc(var1));
            }
            break;
         case 'N':
            var5 = this.A(var1);
            break;
         case 'S':
            if (var1.A() == 't') {
               var1.pC(2);
               var5 = this.pC(var1, "std", 3);
               var1.sY += 3;
            }

            if (var1.pC() == 'S') {
               var6 = this.UT(var1, 0);
               if (var6 == null) {
                  return null;
               }

               if (var6.pC != awg.oB && var6.pC != awg.Rq) {
                  if (var5 != null) {
                     return null;
                  }

                  var4 = true;
                  var5 = var6;
                  var6 = null;
               }
            }

            if (!var4) {
               var5 = this.pC(var1, var5, var6);
            }

            if (var1.pC() == 'I') {
               if (!var4) {
                  var1.pC(var5);
               }

               var5 = this.pC(var1, awg.UT, var5, this.Sc(var1));
               var4 = false;
            }
            break;
         case 'U':
            var5 = this.pC(var1, null, null);
            break;
         case 'Z':
            var5 = this.Ek(var1);
      }

      if (var2 && !var4) {
         var1.pC(var5);
      }

      return var5;
   }

   private awe A(avz var1) {
      awf var2 = new awf(null);
      new awf(null);
      if (!var1.pC('N')) {
         return null;
      } else {
         awf var3;
         awe var4;
         if (var1.pC() == 'H') {
            var1.pC(1);
            var1.sY = var1.sY + "this".length();
            var3 = var2;
            var4 = this.pC(var1, awg.os, null, null);
         } else {
            var3 = this.pC(var1, var2, true, false);
            if (var3 == null) {
               return null;
            }

            var4 = this.kS(var1, null);
         }

         var3.A(this.wS(var1, true));
         if (var3.A() == null) {
            return null;
         } else {
            if (var4 != null) {
               var4.wS.oT().pC = var2.pC();
               var2.pC(var4);
            }

            return !var1.pC('E') ? null : var2.pC();
         }
      }
   }

   private awe wS(avz var1, boolean var2) {
      awe var3 = null;
      int var4 = 0;

      while (true) {
         while (true) {
            char var5 = var1.pC();
            if (var5 == 'D' && (var1.A() == 'T' || var1.A() == 't')) {
               if (var3 != null) {
                  return null;
               }

               var3 = this.gp(var1);
               break;
            }

            if (var5 == 'I') {
               if (var3 == null) {
                  return null;
               }

               awe var7 = this.Sc(var1);
               if (var7 == null) {
                  return null;
               }

               var3 = this.pC(var1, awg.UT, var3, var7);
               break;
            }

            if (var5 == 'T') {
               if (var3 != null) {
                  return null;
               }

               var3 = this.ED(var1);
               break;
            }

            if (var5 == 'M') {
               var1.pC(1);
            } else {
               awe var6 = null;
               if (var5 == 'S') {
                  var6 = this.UT(var1, 1);
                  if (var6 == null) {
                     return null;
                  }

                  if (var6.pC != awg.oB && var6.pC != awg.Rq) {
                     if (var3 != null) {
                        return null;
                     }

                     var3 = var6;
                     continue;
                  }
               }

               var3 = this.pC(var1, var3, var6);
               break;
            }
         }

         if (var3 == null || var1.pC() == 'E') {
            return var3;
         }

         if (var2) {
            var1.pC(var3);
         }

         if (++var4 > 1000) {
            return null;
         }
      }
   }

   private boolean pC(avz var1, awf var2) {
      while (var1.pC() == 'W') {
         var1.pC(1);
         awg var3 = awg.oB;
         if (var1.pC() == 'P') {
            var3 = awg.Rq;
            var1.pC(1);
         }

         var2.A(this.pC(var1, var3, var2.pC(), this.kS(var1)));
         if (var2.A() == null) {
            return false;
         }

         if (!var1.pC(var2.A())) {
            return false;
         }
      }

      return true;
   }

   private awe pC(avz var1, awe var2, awe var3) {
      awe var4 = null;
      boolean var5 = false;
      awf var6 = new awf(var3);
      if (!this.pC(var1, var6)) {
         return null;
      } else {
         var3 = var6.pC();
         char var7 = var1.pC();
         if (var7 == 'F') {
            var5 = true;
            var1.pC(1);
            var7 = var1.pC();
         }

         if (Character.isDigit(var7)) {
            var4 = this.kS(var1);
         } else if (Character.isLowerCase(var7)) {
            boolean var8 = var1.ys;
            if (var7 == 'o' && var1.A() == 'n') {
               var1.pC(2);
               var1.ys = false;
            }

            var4 = this.E(var1);
            var1.ys = var8;
            if (var4 != null && var4.pC == awg.kU) {
               var1.sY = var1.sY + ("operator".length() + var4.wS.A().pC.A.length() - 2);
               if (var4.wS.A().pC.pC.equals("li")) {
                  var4 = this.pC(var1, awg.pg, var4, this.kS(var1));
               }
            }
         } else if (var7 == 'D' && var1.A() == 'C') {
            var1.pC(2);
            awe var11 = null;

            awe var9;
            do {
               var9 = this.pC(var1, awg.KK, this.kS(var1), null);
               if (var11 != null) {
                  var11.wS.oT().A = var9;
               } else {
                  var4 = var9;
               }

               var11 = var9;
            } while (var9 != null && var1.pC() != 'E');

            if (var9 != null) {
               var1.pC(1);
            } else {
               var4 = null;
            }
         } else if (var7 == 'C' || var7 == 'D') {
            var4 = this.ys(var1);
         } else if (var7 == 'L') {
            var1.pC(1);
            var4 = this.kS(var1);
            if (var4 == null) {
               return null;
            }

            if (!this.hK(var1)) {
               return null;
            }
         } else {
            if (var7 != 'U') {
               return null;
            }

            switch (var1.A()) {
               case 'l':
                  var4 = this.Er(var1);
                  break;
               case 't':
                  var4 = this.FE(var1);
                  break;
               default:
                  return null;
            }
         }

         if (var3 != null) {
            var4 = this.pC(var1, awg.LL, var4, var3);
         }

         if (var1.pC() == 'B') {
            var4 = this.A(var1, var4);
         }

         if (var5) {
            var4 = this.pC(var1, awg.IE, var4, null);
         }

         if (var2 != null) {
            var4 = this.pC(var1, awg.A, var2, var4);
         }

         return var4;
      }
   }

   private awe kS(avz var1) {
      int var3 = this.wS(var1);
      if (var3 <= 0) {
         return null;
      } else {
         awe var2 = this.kS(var1, var3);
         var1.E = var2;
         return var2;
      }
   }

   private int wS(avz var1) {
      boolean var2 = false;
      int var3 = 0;
      char var4 = var1.pC();
      if (var4 == 'n') {
         var2 = true;
         var1.pC(1);
         var4 = var1.pC();
      }

      int var5 = 0;

      while (Character.isDigit(var4)) {
         if (var3 > (Integer.MAX_VALUE - (var4 - '0')) / 10) {
            return -1;
         }

         var3 = var3 * 10 + var4 - 48;
         var1.pC(1);
         var4 = var1.pC();
         if (++var5 > 1000) {
            return -1;
         }
      }

      if (var2) {
         var3 = -var3;
      }

      return var3;
   }

   private awe UT(avz var1) {
      awe var2 = this.pC(var1);
      var2.pC = awg.Ip;
      var2.wS = new awe.yt();
      var2.wS.ld().pC = this.wS(var1);
      return var2;
   }

   private awe kS(avz var1, int var2) {
      String var3 = var1.wS();
      if (!var3.isEmpty() && var3.length() < var2) {
         return null;
      } else {
         var1.pC(var2);
         if (var2 >= A + 2
            && var3.startsWith("_GLOBAL_")
            && (var3.charAt(A) == '.' || var3.charAt(A) == '_' || var3.charAt(A) == '$')
            && var3.charAt(A + 1) == 'N') {
            var1.sY = var1.sY - (var2 - "(anonymous namespace)".length() + 1);
            return this.pC(var1, "(anonymous namespace)", "(anonymous namespace)".length());
         } else {
            return this.pC(var1, var3, var2);
         }
      }
   }

   private awe E(avz var1) {
      char var2 = var1.kS();
      char var3 = var1.kS();
      if (var2 == 'v' && Character.isDigit(var3)) {
         return this.pC(var1, var3 - '0', this.kS(var1));
      } else if (var2 == 'c' && var3 == 'v') {
         boolean var7 = var1.ld;
         var1.ld = !var1.ys;
         awe var5 = this.gp(var1);
         awe var6;
         if (var1.ld) {
            var6 = this.pC(var1, awg.JF, var5, null);
         } else {
            var6 = this.pC(var1, awg.go, var5, null);
         }

         var1.ld = var7;
         return var6;
      } else {
         awh var4 = awh.pC("" + var2 + var3);
         return var4 == null ? null : this.pC(var1, var4);
      }
   }

   private awe sY(avz var1) {
      var1.sY += 20;
      if (var1.pC('T')) {
         switch (var1.kS()) {
            case 'A':
               return this.pC(var1, awg.e, this.eP(var1), null);
            case 'B':
            case 'D':
            case 'E':
            case 'G':
            case 'K':
            case 'L':
            case 'M':
            case 'N':
            case 'O':
            case 'P':
            case 'Q':
            case 'R':
            case 'U':
            case 'X':
            case 'Y':
            case 'Z':
            case '[':
            case '\\':
            case ']':
            case '^':
            case '_':
            case '`':
            case 'a':
            case 'b':
            case 'd':
            case 'e':
            case 'f':
            case 'g':
            case 'i':
            case 'j':
            case 'k':
            case 'l':
            case 'm':
            case 'n':
            case 'o':
            case 'p':
            case 'q':
            case 'r':
            case 's':
            case 't':
            case 'u':
            default:
               return null;
            case 'C':
               awe var5 = this.gp(var1);
               int var6 = this.wS(var1);
               if (var6 < 0) {
                  return null;
               } else {
                  if (!var1.pC('_')) {
                     return null;
                  }

                  awe var4 = this.gp(var1);
                  var1.sY += 5;
                  return this.pC(var1, awg.fI, var4, var5);
               }
            case 'F':
               return this.pC(var1, awg.vP, this.gp(var1), null);
            case 'H':
               return this.pC(var1, awg.UO, this.kS(var1, false), null);
            case 'I':
               return this.pC(var1, awg.WR, this.gp(var1), null);
            case 'J':
               return this.pC(var1, awg.ah, this.gp(var1), null);
            case 'S':
               return this.pC(var1, awg.NS, this.gp(var1), null);
            case 'T':
               var1.sY -= 10;
               return this.pC(var1, awg.oT, this.gp(var1), null);
            case 'V':
               var1.sY -= 5;
               return this.pC(var1, awg.gp, this.gp(var1), null);
            case 'W':
               return this.pC(var1, awg.Ab, this.kS(var1, false), null);
            case 'c':
               if (!this.wS(var1, 0)) {
                  return null;
               } else {
                  if (!this.wS(var1, 0)) {
                     return null;
                  }

                  return this.pC(var1, awg.Sc, this.A(var1, false), null);
               }
            case 'h':
               if (!this.wS(var1, 104)) {
                  return null;
               }

               return this.pC(var1, awg.xC, this.A(var1, false), null);
            case 'v':
               return !this.wS(var1, 118) ? null : this.pC(var1, awg.ED, this.A(var1, false), null);
         }
      } else if (var1.pC('G')) {
         switch (var1.kS()) {
            case 'A':
               return this.pC(var1, awg.z, this.A(var1, false), null);
            case 'I':
               awf var3 = new awf(null);
               if (!this.pC(var1, var3)) {
                  return null;
               } else {
                  if (var3.pC() == null) {
                     return null;
                  }

                  return this.pC(var1, awg.rC, var3.pC(), null);
               }
            case 'R':
               awe var2 = this.pC(var1, awg.rl, this.kS(var1, false), this.UT(var1));
               if (var1.pC() == '_') {
                  var1.pC(1);
               }

               return var2;
            case 'T':
               switch (var1.kS()) {
                  case 'n':
                     return this.pC(var1, awg.Ta, this.A(var1, false), null);
                  case 't':
                  default:
                     return this.pC(var1, awg.Um, this.A(var1, false), null);
               }
            case 'V':
               return this.pC(var1, awg.eP, this.kS(var1, false), null);
            case 'r':
               throw new RuntimeException("TBI: java resource");
            default:
               return null;
         }
      } else {
         return null;
      }
   }

   private boolean wS(avz var1, int var2) {
      if (var2 == 0) {
         var2 = var1.kS();
      }

      if (var2 == 104) {
         this.wS(var1);
      } else {
         if (var2 != 118) {
            return false;
         }

         this.wS(var1);
         if (!var1.pC('_')) {
            return false;
         }

         this.wS(var1);
      }

      return var1.pC('_');
   }

   private awe ys(avz var1) {
      if (var1.E != null) {
         if (var1.E.pC == awg.pC) {
            var1.sY = var1.sY + var1.E.wS.pC().pC.length();
         } else if (var1.E.pC == awg.Ek) {
            var1.sY = var1.sY + var1.E.wS.ys().pC.length();
         }
      }

      switch (var1.pC()) {
         case 'C':
            boolean var3 = false;
            if (var1.A() == 'I') {
               var3 = true;
               var1.pC(1);
            }

            awe.oP var4;
            switch (var1.A()) {
               case '1':
                  var4 = awe.oP.pC;
                  break;
               case '2':
                  var4 = awe.oP.A;
                  break;
               case '3':
                  var4 = awe.oP.kS;
                  break;
               case '4':
                  var4 = awe.oP.wS;
                  break;
               case '5':
                  var4 = awe.oP.UT;
                  break;
               default:
                  return null;
            }

            var1.pC(2);
            if (var3) {
               this.gp(var1);
            }

            return this.pC(var1, var4, var1.E);
         case 'D':
            awe.vi var2;
            switch (var1.A()) {
               case '0':
                  var2 = awe.vi.pC;
                  break;
               case '1':
                  var2 = awe.vi.A;
                  break;
               case '2':
                  var2 = awe.vi.kS;
                  break;
               case '3':
               default:
                  return null;
               case '4':
                  var2 = awe.vi.wS;
                  break;
               case '5':
                  var2 = awe.vi.UT;
            }

            var1.pC(2);
            return this.pC(var1, var2, var1.E);
         default:
            return null;
      }
   }

   private boolean ld(avz var1) {
      char var2 = var1.pC();
      if (var2 != 'r' && var2 != 'V' && var2 != 'K') {
         if (var2 == 'D') {
            var2 = var1.A();
            if (var2 == 'x' || var2 == 'o' || var2 == 'O' || var2 == 'w') {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   private awe gp(avz var1) {
      return this.UT(var1, true);
   }

   private awe UT(avz var1, boolean var2) {
      if (this.ld(var1)) {
         new awf(null);
         awf var19 = new awf(null);
         awf var18 = this.pC(var1, var19, false, true);
         if (var18 == null) {
            return null;
         } else {
            if (var1.pC() == 'F') {
               var18.A(this.oT(var1));
            } else {
               var18.A(this.gp(var1));
            }

            if (var18.A() == null) {
               return null;
            } else {
               if (var18.A().pC == awg.sO || var18.A().pC == awg.mv) {
                  awe var20 = var18.A().pC();
                  var18.A().wS.oT().pC = var19.pC();
                  var19 = new awf(var18.A());
                  var18.A(var20);
               }

               if (var2) {
                  var1.pC(var19.pC());
               }

               return var19.pC();
            }
         }
      } else {
         awe var4;
         var2 = true;
         char var3 = var1.pC();
         label152:
         switch (var3) {
            case 'A':
               var4 = this.WR(var1);
               break;
            case 'B':
            case 'E':
            case 'H':
            case 'I':
            case 'J':
            case 'K':
            case 'L':
            case 'N':
            case 'Q':
            case 'S':
            case 'V':
            case 'W':
            case 'X':
            case 'Y':
            case 'Z':
            case '[':
            case '\\':
            case ']':
            case '^':
            case '_':
            case '`':
            case 'k':
            case 'p':
            case 'q':
            case 'r':
            default:
               return this.sY(var1, true);
            case 'C':
               var1.pC(1);
               var4 = this.pC(var1, awg.cX, this.gp(var1), null);
               break;
            case 'D':
               var2 = false;
               var1.pC(1);
               var3 = var1.kS();
               byte var21 = 0;
               boolean var22 = false;
               switch (var3) {
                  case 'C':
                  case 'D':
                  case 'E':
                  case 'G':
                  case 'H':
                  case 'I':
                  case 'J':
                  case 'K':
                  case 'L':
                  case 'M':
                  case 'N':
                  case 'O':
                  case 'P':
                  case 'Q':
                  case 'V':
                  case 'W':
                  case 'X':
                  case 'Y':
                  case 'Z':
                  case '[':
                  case '\\':
                  case ']':
                  case '^':
                  case '_':
                  case '`':
                  case 'b':
                  case 'g':
                  case 'j':
                  case 'k':
                  case 'l':
                  case 'm':
                  case 'o':
                  case 'q':
                  case 'r':
                  default:
                     return null;
                  case 'F':
                     int var23 = this.wS(var1);
                     if (var1.pC() == 'b') {
                        if (var23 != 16) {
                           return null;
                        }

                        var1.pC(1);
                        var4 = this.pC(var1, awd.pC(35));
                        var1.sY = var1.sY + var4.wS.E().pC.pC.length();
                     } else {
                        char var24 = 0;
                        if (var1.pC() == 'x') {
                           var24 = 'x';
                        } else if (var1.pC() != '_') {
                           return null;
                        }

                        var4 = this.pC(var1, awd.pC(34), var23, var24);
                        var1.pC(1);
                        String var25 = Integer.toString(var23);
                        var1.sY = var1.sY + var4.wS.sY().pC.pC.length() + var25.length() + (var24 == 0 ? 0 : 1);
                     }
                     break label152;
                  case 'S':
                     var22 = true;
                     if (var1.pC() != 'D') {
                        return null;
                     }

                     var1.pC(1);
                     var3 = var1.pC();
                     if (var3 != 'A' && var3 != 'R') {
                        return null;
                     }

                     var1.pC(1);
                  case 'A':
                  case 'R':
                     var4 = this.pC(var1);
                     var4.pC = awg.Bf;
                     var4.wS = new awe.zp();
                     var4.wS.WR().A = var3 == 'A';
                     var4.wS.WR().kS = var22;
                     var4.wS.WR().pC = this.gp(var1);
                     if (var4.wS.WR().pC == null) {
                        return null;
                     }
                     break label152;
                  case 'T':
                  case 't':
                     var4 = this.pC(var1, awg.Fm, this.rl(var1), null);
                     if (var4 != null && var1.kS() != 'E') {
                        var4 = null;
                     }

                     var2 = true;
                     break label152;
                  case 'U':
                     var21 = 1;
                  case 'B':
                     int var10 = var1.kS;
                     int var11 = this.wS(var1);
                     int var13 = 2;
                     awc var12;
                     if (var10 == var1.kS) {
                        awe var14 = this.rl(var1);
                        if (var14 == null) {
                           return null;
                        }

                        var12 = new awc(var14);
                     } else {
                        var12 = new awc((short)var11);
                        String var26 = Integer.toString(var11);
                        var13 += var26.length();
                     }

                     if (var1.pC() != '_') {
                        return null;
                     }

                     var4 = this.pC(var1, awd.pC(36 + var21), var12, '\u0000');
                     var4.wS.sY().kS = true;
                     var1.pC(1);
                     var1.sY = var1.sY + var4.wS.sY().pC.pC.length() + var13;
                     break label152;
                  case 'a':
                     var4 = this.pC(var1, "auto", 4);
                     break label152;
                  case 'c':
                     var4 = this.pC(var1, "decltype(auto)", 14);
                     break label152;
                  case 'd':
                     var4 = this.pC(var1, awd.pC(27));
                     var1.sY = var1.sY + var4.wS.E().pC.pC.length();
                     break label152;
                  case 'e':
                     var4 = this.pC(var1, awd.pC(28));
                     var1.sY = var1.sY + var4.wS.E().pC.pC.length();
                     break label152;
                  case 'f':
                     var4 = this.pC(var1, awd.pC(26));
                     var1.sY = var1.sY + var4.wS.E().pC.pC.length();
                     break label152;
                  case 'h':
                     var4 = this.pC(var1, awd.pC(29));
                     var1.sY = var1.sY + var4.wS.E().pC.pC.length();
                     break label152;
                  case 'i':
                     var4 = this.pC(var1, awd.pC(32));
                     var1.sY = var1.sY + var4.wS.E().pC.pC.length();
                     break label152;
                  case 'n':
                     var4 = this.pC(var1, awd.pC(33));
                     var1.sY = var1.sY + var4.wS.E().pC.pC.length();
                     break label152;
                  case 'p':
                     var4 = this.pC(var1, awg.So, this.gp(var1), null);
                     var2 = true;
                     break label152;
                  case 's':
                     var4 = this.pC(var1, awd.pC(31));
                     var1.sY = var1.sY + var4.wS.E().pC.pC.length();
                     break label152;
                  case 'u':
                     var4 = this.pC(var1, awd.pC(30));
                     var1.sY = var1.sY + var4.wS.E().pC.pC.length();
                     break label152;
                  case 'v':
                     var4 = this.NS(var1);
                     var2 = true;
                     break label152;
               }
            case 'F':
               var4 = this.oT(var1);
               break;
            case 'G':
               var1.pC(1);
               var4 = this.pC(var1, awg.DQ, this.gp(var1), null);
               break;
            case 'M':
               var4 = this.vP(var1);
               break;
            case 'O':
               var1.pC(1);
               var4 = this.pC(var1, awg.PR, this.gp(var1), null);
               break;
            case 'P':
               var1.pC(1);
               var4 = this.pC(var1, awg.hZ, this.gp(var1), null);
               break;
            case 'R':
               var1.pC(1);
               var4 = this.pC(var1, awg.UW, this.gp(var1), null);
               break;
            case 'T':
               char var7 = var1.A();
               if (var7 == 's' || var7 == 'u' || var7 == 'e') {
                  return this.sY(var1, true);
               }

               var4 = this.ED(var1);
               if (var1.pC() == 'I') {
                  if (!var1.ld) {
                     var1.pC(var4);
                     var4 = this.pC(var1, awg.UT, var4, this.Sc(var1));
                  } else {
                     avz var8 = new avz(var1);
                     awe var9 = this.Sc(var1);
                     if (var1.pC() == 'I') {
                        var1.pC(var4);
                        var4 = this.pC(var1, awg.UT, var4, var9);
                     } else {
                        var1.kS = var8.kS;
                        var1.wS = var8.wS;
                        var1.UT = var8.UT;
                        var1.sY = var8.sY;
                     }
                  }
               }
               break;
            case 'U':
               var1.pC(1);
               var4 = this.kS(var1);
               if (var1.pC() == 'I') {
                  var4 = this.pC(var1, awg.UT, var4, this.Sc(var1));
               }

               var4 = this.pC(var1, awg.Cu, this.UT(var1, false), var4);
               break;
            case 'a':
            case 'b':
            case 'c':
            case 'd':
            case 'e':
            case 'f':
            case 'g':
            case 'h':
            case 'i':
            case 'j':
            case 'l':
            case 'm':
            case 'n':
            case 'o':
            case 's':
            case 't':
            case 'v':
            case 'w':
            case 'x':
            case 'y':
            case 'z':
               var4 = this.pC(var1, awd.pC(var3 - 'a'));
               var1.sY = var1.sY + var4.wS.E().pC.pC.length();
               var2 = false;
               var1.pC(1);
               break;
            case 'u':
               var1.pC(1);
               awe var5 = this.kS(var1);
               awe var6 = this.Sc(var1);
               var4 = this.pC(var1, awg.OB, var5, var6);
         }

         if (var2) {
            var1.pC(var4);
         }

         return var4;
      }
   }

   private awf pC(avz var1, awf var2, boolean var3, boolean var4) {
      awf var5 = var2;
      char var6 = var1.pC();
      int var7 = 0;

      while (this.ld(var1)) {
         awe var9 = null;
         var1.pC(1);
         awg var8;
         if (var6 == 'r') {
            var8 = var3 ? awg.Aj : awg.hK;
            var1.sY = var1.sY + "restrict".length();
         } else if (var6 == 'V') {
            var8 = var3 ? awg.EX : awg.Er;
            var1.sY = var1.sY + "volatile".length();
         } else if (var6 == 'K') {
            var8 = var3 ? awg.LM : awg.FE;
            var1.sY = var1.sY + "const".length();
         } else {
            if (!var4) {
               break;
            }

            var6 = var1.kS();
            if (var6 == 'x') {
               var8 = awg.Gm;
               var1.sY = var1.sY + "transaction_safe".length();
            } else if (var6 != 'o' && var6 != 'O') {
               if (var6 != 'w') {
                  return null;
               }

               var8 = awg.jS;
               var1.sY = var1.sY + "throw".length();
               var9 = this.fI(var1);
               if (var9 == null) {
                  return null;
               }

               if (!var1.pC('E')) {
                  return null;
               }
            } else {
               var8 = awg.AU;
               var1.sY = var1.sY + "noexcept".length();
               if (var6 == 'O') {
                  var9 = this.rl(var1);
                  if (var9 == null) {
                     return null;
                  }

                  if (!var1.pC('E')) {
                     return null;
                  }
               }
            }
         }

         var2.A(this.pC(var1, var8, null, var9));
         if (var2.A() == null) {
            return null;
         }

         awe var10 = var2.A();
         var2 = new awf(null);
         var2.pC(var10, true, false);
         var6 = var1.pC();
         if (++var7 > 1000) {
            return null;
         }
      }

      if (!var3 && var6 == 'F') {
         while (var5.A() != var2.A()) {
            switch (var5.A().pC) {
               case hK:
                  var5.A().pC = awg.Aj;
                  break;
               case Er:
                  var5.A().pC = awg.EX;
                  break;
               case FE:
                  var5.A().pC = awg.LM;
            }

            awe var12 = var5.A();
            var5 = new awf(null);
            var5.pC(var12, true, false);
         }
      }

      return var2;
   }

   private awe kS(avz var1, awe var2) {
      awe var3 = var2;
      char var4 = var1.pC();
      if (var4 == 'R' || var4 == 'O') {
         awg var5;
         if (var4 == 'R') {
            var5 = awg.mv;
            var1.sY = var1.sY + "&".length();
         } else {
            var5 = awg.sO;
            var1.sY = var1.sY + "&&".length();
         }

         var1.pC(1);
         var3 = this.pC(var1, var5, var2, null);
      }

      return var3;
   }

   private awe oT(avz var1) {
      if (!var1.pC('F')) {
         return null;
      } else {
         if (var1.pC() == 'Y') {
            var1.pC(1);
         }

         awe var2 = this.E(var1, true);
         var2 = this.kS(var1, var2);
         return !var1.pC('E') ? null : var2;
      }
   }

   private awe fI(avz var1) {
      awe var2 = null;
      awe var3 = null;
      int var4 = 0;

      do {
         char var6 = var1.pC();
         if (var6 == 0 || var6 == 'E' || var6 == '.' || var6 == 'Q' || (var6 == 'R' || var6 == 'O') && var1.A() == 'E') {
            if (var3 == null) {
               return null;
            } else {
               if (var3.A() == null && var3.pC().pC == awg.ZN && var3.pC().wS.E().pC.kS == awd.Av.gp) {
                  var1.sY = var1.sY - var3.pC().wS.E().pC.pC.length();
                  var3.wS.oT().pC = null;
               }

               return var3;
            }
         }

         awe var5 = this.gp(var1);
         if (var5 == null) {
            return null;
         }

         if (var2 == null) {
            var2 = this.pC(var1, awg.ck, var5, null);
            if (var2 == null) {
               return null;
            }

            var3 = var2;
         } else {
            var2.wS.oT().A = this.pC(var1, awg.ck, var5, null);
            if (var2.A() == null) {
               return null;
            }

            var2 = var2.A();
         }
      } while (++var4 <= 1000);

      return null;
   }

   private awe E(avz var1, boolean var2) {
      awe var3 = null;
      char var5 = var1.pC();
      if (var5 == 'J') {
         var1.pC(1);
         var2 = true;
      }

      if (var2) {
         var3 = this.gp(var1);
         if (var3 == null) {
            return null;
         }
      }

      awe var4 = this.fI(var1);
      return var3 == null && var4 == null ? null : this.pC(var1, awg.pF, var3, var4);
   }

   private awe sY(avz var1, boolean var2) {
      awg var3 = null;
      if (var1.pC() == 'T') {
         char var4 = var1.A();
         if (var4 == 's') {
            var3 = awg.dM;
            var1.pC(2);
         } else if (var4 == 'u') {
            var3 = awg.EM;
            var1.pC(2);
         } else if (var4 == 'e') {
            var3 = awg.fD;
            var1.pC(2);
         }
      }

      awe var5 = this.kS(var1, var2);
      return var3 != null ? this.pC(var1, var3, var5, null) : var5;
   }

   private awe WR(avz var1) {
      awe var2 = null;
      if (!var1.pC('A')) {
         return null;
      } else {
         char var3 = var1.pC();
         if (var3 != '_') {
            if (Character.isDigit(var3)) {
               String var4 = var1.wS();

               do {
                  var1.pC(1);
                  var3 = var1.pC();
               } while (Character.isDigit(var3));

               int var5 = var4.length() - var1.wS().length();
               var2 = this.pC(var1, var4, var5);
               if (var2 == null) {
                  return null;
               }
            } else {
               var2 = this.rl(var1);
               if (var2 == null) {
                  return null;
               }
            }
         }

         return !var1.pC('_') ? null : this.pC(var1, awg.Bc, var2, this.gp(var1));
      }
   }

   private awe NS(avz var1) {
      char var3 = var1.pC();
      awe var2;
      if (var3 == '_') {
         var1.pC(1);
         var2 = this.rl(var1);
      } else {
         var2 = this.UT(var1);
      }

      if (var2 == null) {
         return null;
      } else {
         return !var1.pC('_') ? null : this.pC(var1, awg.Pe, var2, this.gp(var1));
      }
   }

   private awe vP(avz var1) {
      if (!var1.pC('M')) {
         return null;
      } else {
         awe var2 = this.gp(var1);
         if (var2 == null) {
            return null;
         } else {
            awe var3 = this.gp(var1);
            return var3 == null ? null : this.pC(var1, awg.OI, var2, var3);
         }
      }
   }

   private int xC(avz var1) {
      int var2;
      if (var1.pC() == '_') {
         var2 = 0;
      } else {
         if (var1.pC() == 'n') {
            return -1;
         }

         var2 = this.wS(var1) + 1;
      }

      return var2 >= 0 && var1.pC((char)95) ? var2 : -1;
   }

   private awe ED(avz var1) {
      if (!var1.pC('T')) {
         return null;
      } else {
         int var2 = this.xC(var1);
         return var2 < 0 ? null : this.pC(var1, var2);
      }
   }

   private awe Sc(avz var1) {
      if (var1.pC() != 'I' && var1.pC() != 'J') {
         return null;
      } else {
         var1.pC(1);
         return this.ah(var1);
      }
   }

   private awe ah(avz var1) {
      awe var2 = var1.E;
      if (var1.pC() == 'E') {
         var1.pC(1);
         return this.pC(var1, awg.RW, null, null);
      } else {
         awe var3 = null;
         awe var4 = null;
         int var5 = 0;

         do {
            awe var6 = this.eP(var1);
            if (var6 == null) {
               return null;
            }

            if (var3 == null) {
               var3 = this.pC(var1, awg.RW, var6, null);
               if (var3 == null) {
                  return null;
               }

               var4 = var3;
            } else {
               var4.wS.oT().A = this.pC(var1, awg.RW, var6, null);
               if (var4.A() == null) {
                  return null;
               }

               var4 = var4.A();
            }

            char var7 = var1.pC();
            if (var7 == 'E' || var7 == 'Q') {
               var3 = this.pC(var1, var3);
               if (var1.pC() != 'E') {
                  return null;
               }

               var1.pC(1);
               var1.E = var2;
               return var3;
            }
         } while (++var5 <= 1000);

         return null;
      }
   }

   private awe eP(avz var1) {
      switch (var1.pC()) {
         case 'I':
         case 'J':
            return this.Sc(var1);
         case 'L':
            return this.z(var1);
         case 'X':
            var1.pC(1);
            awe var2 = this.rl(var1);
            if (!var1.pC('E')) {
               return null;
            }

            return var2;
         default:
            return this.gp(var1);
      }
   }

   private awe pC(avz var1, char var2) {
      awe var3 = null;
      awe var4 = null;
      if (var1.pC() == var2) {
         var1.pC(1);
         return this.pC(var1, awg.ck, null, null);
      } else {
         int var5 = 0;

         do {
            awe var6 = this.rl(var1);
            if (var6 == null) {
               return null;
            }

            if (var3 == null) {
               var3 = this.pC(var1, awg.ck, var6, null);
               if (var3 == null) {
                  return null;
               }

               var4 = var3;
            } else {
               var4.wS.oT().A = this.pC(var1, awg.ck, var6, null);
               if (var4.A() == null) {
                  return null;
               }

               var4 = var4.A();
            }

            if (var1.pC() == var2) {
               var1.pC(1);
               return var3;
            }
         } while (++var5 <= 1000);

         return null;
      }
   }

   static boolean pC(awe var0) {
      String var1 = var0.wS.A().pC.pC;
      return var1.charAt(1) == 'c' && (var1.charAt(0) == 's' || var1.charAt(0) == 'd' || var1.charAt(0) == 'c' || var1.charAt(0) == 'r');
   }

   private awe UO(avz var1) {
      var1.pC(2);
      char var3 = var1.pC();
      awe var2;
      if (var1.gp != 0 && (Character.isDigit(var3) || Character.isLowerCase(var3) || var3 == 'C' || var3 == 'U' || var3 == 'L')) {
         var1.gp = -1;
         var2 = this.wS(var1, false);
         if (var1.pC() == 'E') {
            var1.pC(1);
         }
      } else {
         var2 = this.gp(var1);
      }

      awe var4 = this.pC(var1, var2, null);
      if (var1.pC() == 'I') {
         var4 = this.pC(var1, awg.UT, var4, this.Sc(var1));
      }

      return var4;
   }

   private awe Ab(avz var1) {
      char var2 = var1.pC();
      if (var2 == 'L') {
         return this.z(var1);
      } else if (var2 == 'T') {
         return this.ED(var1);
      } else if (var2 == 's' && var1.A() == 'r') {
         return this.UO(var1);
      } else if (var2 == 's' && var1.A() == 'p') {
         var1.pC(2);
         return this.pC(var1, awg.So, this.Ab(var1), null);
      } else if (var2 == 'f' && var1.A() == 'p') {
         var1.pC(2);
         int var14;
         if (var1.pC() == 'T') {
            var1.pC(1);
            var14 = 0;
         } else {
            var14 = this.xC(var1);
            if (var14 == Integer.MAX_VALUE || var14 == -1) {
               return null;
            }

            var14++;
         }

         return this.A(var1, var14);
      } else if (!Character.isDigit(var2) && (var2 != 'o' || var1.A() != 'n')) {
         if ((var2 == 'i' || var2 == 't') && var1.A() == 'l') {
            awe var12 = null;
            var1.pC(2);
            if (var2 == 't') {
               var12 = this.gp(var1);
            }

            return var1.pC() != 0 && var1.A() != 0 ? this.pC(var1, awg.xM, var12, this.pC(var1, 'E')) : null;
         } else if (var2 == 'u') {
            var1.pC(1);
            awe var11 = this.kS(var1);
            awe var15 = this.ah(var1);
            return this.pC(var1, awg.FK, var11, var15);
         } else {
            awe var10 = this.E(var1);
            if (var10 == null) {
               return null;
            } else {
               String var4 = null;
               if (var10.pC == awg.kU) {
                  var4 = var10.wS.A().pC.pC;
                  var1.sY = var1.sY + (var10.wS.A().pC.A.length() - 2);
                  if (var4.equals("st")) {
                     return this.pC(var1, awg.pg, var10, this.gp(var1));
                  }
               }

               int var5;
               switch (var10.pC) {
                  case kU:
                     var5 = var10.wS.A().pC.kS;
                     break;
                  case Kq:
                     var5 = var10.wS.kS().pC;
                     break;
                  case go:
                     var5 = 1;
                     break;
                  default:
                     return null;
               }

               switch (var5) {
                  case 0:
                     return this.pC(var1, awg.Nq, var10, null);
                  case 1:
                     boolean var17 = false;
                     if (var4 != null && (var4.charAt(0) == 'p' || var4.charAt(0) == 'm') && var4.charAt(1) == var4.charAt(0)) {
                        var17 = !var1.pC('_');
                     }

                     awe var19;
                     if (var10.pC == awg.go && var1.pC('_')) {
                        var19 = this.pC(var1, 'E');
                     } else if (var4 != null && var4.equals("sP")) {
                        var19 = this.ah(var1);
                     } else {
                        var19 = this.Ab(var1);
                     }

                     if (var17) {
                        var19 = this.pC(var1, awg.ZD, var19, var19);
                     }

                     return this.pC(var1, awg.pg, var10, var19);
                  case 2:
                     if (var4 == null) {
                        return null;
                     }

                     awe var16;
                     if (pC(var10)) {
                        var16 = this.gp(var1);
                     } else if (var4.charAt(0) == 'f') {
                        var16 = this.E(var1);
                     } else if (var4.equals("di")) {
                        var16 = this.pC(var1, null, null);
                     } else {
                        var16 = this.Ab(var1);
                     }

                     awe var18;
                     if (var4.equals("cl")) {
                        var18 = this.pC(var1, 'E');
                     } else if (!var4.equals("dt") && !var4.equals("pt")) {
                        var18 = this.Ab(var1);
                     } else {
                        var2 = var1.pC();
                        if ((var2 != 'g' || var1.A() != 's') && (var2 != 's' || var1.A() != 'r')) {
                           var18 = this.pC(var1, null, null);
                           if (var1.pC() == 'I') {
                              var18 = this.pC(var1, awg.UT, var18, this.Sc(var1));
                           }
                        } else {
                           var18 = this.Ab(var1);
                        }
                     }

                     return this.pC(var1, awg.gj, var10, this.pC(var1, awg.ZD, var16, var18));
                  case 3:
                     awe var8 = null;
                     if (var4 == null) {
                        return null;
                     } else {
                        awe var6;
                        awe var7;
                        if (!var4.equals("qu") && !var4.equals("dX")) {
                           if (var4.charAt(0) == 'f') {
                              var6 = this.E(var1);
                              var7 = this.Ab(var1);
                              var8 = this.Ab(var1);
                              if (var8 == null) {
                                 return null;
                              }
                           } else {
                              if (var4.charAt(0) != 'n') {
                                 return null;
                              }

                              if (var4.charAt(1) != 'w' && var4.charAt(1) != 'a') {
                                 return null;
                              }

                              var6 = this.pC(var1, '_');
                              var7 = this.gp(var1);
                              if (var1.pC() == 'E') {
                                 var1.pC(1);
                              } else if (var1.pC() == 'p' && var1.A() == 'i') {
                                 var1.pC(2);
                                 var8 = this.pC(var1, 'E');
                              } else {
                                 if (var1.pC() != 'i' || var1.A() != 'l') {
                                    return null;
                                 }

                                 var8 = this.Ab(var1);
                              }
                           }
                        } else {
                           var6 = this.Ab(var1);
                           var7 = this.Ab(var1);
                           var8 = this.Ab(var1);
                           if (var8 == null) {
                              return null;
                           }
                        }

                        return this.pC(var1, awg.DL, var10, this.pC(var1, awg.UH, var6, this.pC(var1, awg.VD, var7, var8)));
                     }
                  default:
                     return null;
               }
            }
         }
      } else {
         if (var2 == 'o') {
            var1.pC(2);
         }

         awe var3 = this.pC(var1, null, null);
         if (var3 == null) {
            return null;
         } else {
            return var1.pC() == 'I' ? this.pC(var1, awg.UT, var3, this.Sc(var1)) : var3;
         }
      }
   }

   private awe rl(avz var1) {
      boolean var2 = var1.ys;
      var1.ys = true;
      awe var3 = this.Ab(var1);
      var1.ys = var2;
      return var3;
   }

   private awe z(avz var1) {
      if (!var1.pC('L')) {
         return null;
      } else {
         awe var2;
         if (var1.pC() != '_' && var1.pC() != 'Z') {
            awe var3 = this.gp(var1);
            if (var3 == null) {
               return null;
            }

            if (var3.pC == awg.ZN && var3.wS.E().pC.kS != awd.Av.pC) {
               var1.sY = var1.sY - var3.wS.E().pC.pC.length();
            }

            if (var3.pC == awg.ZN && var3.wS.E().pC.pC.equals(awd.pC(33).pC) && var1.pC() == 'E') {
               var1.pC(1);
               return var3;
            }

            awg var4 = awg.Xs;
            if (var1.pC() == 'n') {
               var4 = awg.KV;
               var1.pC(1);
            }

            String var5 = var1.wS();

            while (var1.pC() != 'E') {
               if (var1.pC() == 0) {
                  return null;
               }

               var1.pC(1);
            }

            int var6 = var5.length() - var1.wS().length();
            var2 = this.pC(var1, var4, var3, this.pC(var1, var5, var6));
         } else {
            var2 = this.pC(var1, false);
         }

         return !var1.pC('E') ? null : var2;
      }
   }

   private awe Ek(avz var1) {
      if (!var1.pC('Z')) {
         return null;
      } else {
         awe var2 = this.A(var1, false);
         if (var2 == null) {
            return null;
         } else if (!var1.pC('E')) {
            return null;
         } else {
            awe var3;
            if (var1.pC() == 's') {
               var1.pC(1);
               if (!this.hK(var1)) {
                  return null;
               }

               var3 = this.pC(var1, "string literal", "string literal".length());
            } else {
               int var4 = -1;
               if (var1.pC() == 'd') {
                  var1.pC(1);
                  var4 = this.xC(var1);
                  if (var4 < 0) {
                     return null;
                  }
               }

               var3 = this.kS(var1, false);
               if (var3 != null && var3.pC != awg.gy && var3.pC != awg.uE && !this.hK(var1)) {
                  return null;
               }

               if (var4 >= 0) {
                  var3 = this.A(var1, var4, var3);
               }
            }

            if (var2.pC == awg.wS && var2.A().pC == awg.pF) {
               awe var5 = var2.A();
               var5.wS.oT().pC = null;
            }

            return this.pC(var1, awg.kS, var2, var3);
         }
      }
   }

   private boolean hK(avz var1) {
      int var3 = 1;
      if (var1.pC() != '_') {
         return true;
      } else {
         var1.pC(1);
         if (var1.pC() == '_') {
            var3++;
            var1.pC(1);
         }

         int var2 = this.wS(var1);
         if (var2 < 0) {
            return false;
         } else {
            if (var3 > 1 && var2 >= 10) {
               if (var1.pC() != '_') {
                  return false;
               }

               var1.pC(1);
            }

            return true;
         }
      }
   }

   private awe pC(avz var1, awj var2) {
      if (var1.pC() != 'T') {
         return null;
      } else {
         awe var3;
         awg var4;
         switch (var1.A()) {
            case 'n':
               var1.pC(2);
               var3 = this.gp(var1);
               var4 = awg.sz;
               if (var3 == null) {
                  var2.pC = 1;
                  return null;
               }
               break;
            case 'p':
               var1.pC(2);
               var3 = this.pC(var1, var2);
               var4 = awg.eE;
               if (var3 == null) {
                  var2.pC = 1;
                  return null;
               }
               break;
            case 't':
               var1.pC(2);
               var3 = this.A(var1, var2);
               var4 = awg.QQ;
               if (var3 == null || !var1.pC('E')) {
                  var2.pC = 1;
                  return null;
               }
               break;
            case 'y':
               var1.pC(2);
               var3 = null;
               var4 = awg.Xh;
               break;
            default:
               return null;
         }

         return this.pC(var1, var4, var3, null);
      }
   }

   private awe A(avz var1, awj var2) {
      awe var3 = null;

      awe var4;
      while ((var4 = this.pC(var1, var2)) != null) {
         if (var3 == null) {
            var3 = var4;
         } else {
            var3.wS.oT().A = var4;
            var3 = var4;
         }
      }

      awe var5 = var3;
      if (var3 != null) {
         var5 = this.pC(var1, awg.be, var3, null);
      }

      return var5;
   }

   private awe Er(avz var1) {
      if (!var1.pC('U')) {
         return null;
      } else if (!var1.pC('l')) {
         return null;
      } else {
         awj var2 = new awj(0);
         awe var3 = this.A(var1, var2);
         if (var2.pC != 0) {
            return null;
         } else {
            awe var4 = this.fI(var1);
            if (var4 == null) {
               return null;
            } else {
               if (var3 != null) {
                  var3.wS.oT().A = var4;
                  var4 = var3;
               }

               if (!var1.pC('E')) {
                  return null;
               } else {
                  int var5 = this.xC(var1);
                  if (var5 < 0) {
                     return null;
                  } else {
                     awe var6 = this.pC(var1);
                     if (var6 != null) {
                        var6.pC = awg.gy;
                        var6.wS = new awe.HE();
                        var6.wS.fI().pC = var4;
                        var6.wS.fI().A = var5;
                     }

                     return var6;
                  }
               }
            }
         }
      }
   }

   private awe FE(avz var1) {
      if (!var1.pC('U')) {
         return null;
      } else if (!var1.pC('t')) {
         return null;
      } else {
         int var2 = this.xC(var1);
         if (var2 < 0) {
            return null;
         } else {
            awe var3 = this.pC(var1);
            if (var3 != null) {
               var3.pC = awg.uE;
               var3.wS = new awe.yt();
               var3.wS.ld().pC = var2;
            }

            var1.pC(var3);
            return var3;
         }
      }
   }

   private awe wS(avz var1, awe var2) {
      String var3 = var1.wS();
      var3 = var3 + "\u0000";
      String var4 = var3;
      if (var3.startsWith(".") && (Character.isLowerCase(var3.charAt(1)) || Character.isDigit(var3.charAt(1)) || var3.charAt(1) == '_')) {
         var4 = var3.substring(2);

         while (Character.isLowerCase(var4.charAt(0)) || Character.isDigit(var4.charAt(0)) || var4.charAt(0) == '_') {
            var4 = var4.substring(1);
         }
      }

      while (var4.startsWith(".") && Character.isDigit(var4.charAt(1))) {
         var4 = var4.substring(2);

         while (Character.isDigit(var4.charAt(0))) {
            var4 = var4.substring(1);
         }
      }

      var1.pC(var3.length() - var4.length());
      awe var5 = this.pC(var1, var3, var3.length() - var4.length());
      return this.pC(var1, awg.Br, var2, var5);
   }

   private awe UT(avz var1, int var2) {
      if (!var1.pC('S')) {
         return null;
      } else {
         char var3 = var1.kS();
         if (var3 != '_' && !Character.isDigit(var3) && !Character.isUpperCase(var3)) {
            boolean var9 = false;
            if (!var9 && var2 != 0) {
               char var10 = var1.pC();
               if (var10 == 'C' || var10 == 'D') {
                  var9 = true;
               }
            }

            for (awb var12 : awb.UT) {
               if (var12.pC == var3) {
                  if (var12.wS != null) {
                     var1.E = this.pC(var1, var12.wS);
                  }

                  String var7;
                  if (var9) {
                     var7 = var12.kS;
                  } else {
                     var7 = var12.A;
                  }

                  var1.sY = var1.sY + var7.length();
                  awe var8 = this.pC(var1, var7);
                  if (var1.pC() == 'B') {
                     var8 = this.A(var1, var8);
                     var1.pC(var8);
                  }

                  return var8;
               }
            }

            return null;
         } else {
            int var4 = 0;
            if (var3 != '_') {
               int var5 = 0;

               int var6;
               do {
                  if (Character.isDigit(var3)) {
                     var6 = var4 * 36 + var3 - 48;
                  } else {
                     if (!Character.isUpperCase(var3)) {
                        return null;
                     }

                     var6 = var4 * 36 + var3 - 65 + 10;
                  }

                  if (var6 < var4) {
                     return null;
                  }

                  var4 = var6;
                  var3 = var1.kS();
                  if (++var5 > 1000) {
                     return null;
                  }
               } while (var3 != '_');

               var4 = var6 + 1;
            }

            return var4 >= var1.UT.size() ? null : (awe)var1.UT.get(var4);
         }
      }
   }
}
