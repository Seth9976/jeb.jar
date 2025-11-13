package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;

class ays {
   private static final ILogger q = GlobalLog.getLogger(ays.class);
   private static final String RF = "_GLOBAL_";
   private static final int xK = "_GLOBAL_".length();

   static boolean q(azd var0) {
      return var0 == azd.wF
         || var0 == azd.If
         || var0 == azd.Dz
         || var0 == azd.mI
         || var0 == azd.jq
         || var0 == azd.ui
         || var0 == azd.fw
         || var0 == azd.PY
         || var0 == azd.cR;
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   private void q(azb var1, int var2) {
      StringBuilder var3 = new StringBuilder();

      for (int var4 = 0; var4 < var2; var4++) {
         var3.append(' ');
      }

      if (var1 != null) {
         switch (var1.q) {
            case q:
               var3.toString() + "name '%s'\n";
               Object[] var83 = new Object[]{var1.Dw.q().q};
               return;
            case YA:
               var3.toString() + "tagged name\n";
               Object[] var82 = new Object[0];
               this.q(var1.q(), var2 + 2);
               this.q(var1.RF(), var2 + 2);
               return;
            case oW:
               var3.toString() + "template parameter %d\n";
               Object[] var81 = new Object[]{var1.Dw.gP().q};
               return;
            case AB:
               Object[] var80 = new Object[0];
               break;
            case gO:
               var3.toString() + "function parameter %d\n";
               Object[] var79 = new Object[]{var1.Dw.gP().q};
               return;
            case nf:
               var3.toString() + "constructor %s\n";
               Object[] var78 = new Object[]{var1.Dw.Dw().q};
               this.q(var1.Dw.Dw().RF, var2 + 2);
               return;
            case gP:
               var3.toString() + "destructor %s\n";
               Object[] var77 = new Object[]{var1.Dw.Uv().q};
               this.q(var1.Dw.Uv().RF, var2 + 2);
               return;
            case Ef:
               var3.toString() + "standard substitution %s\n";
               Object[] var76 = new Object[]{var1.Dw.nf().q};
               return;
            case rL:
               var3.toString() + "builtin type %s\n";
               Object[] var75 = new Object[]{var1.Dw.oW().q.q};
               return;
            case Bs:
               char var6 = var1.Dw.gO().Dw;
               var3.toString() + "builtin type %s%d%s\n";
               Object[] var74 = new Object[]{var1.Dw.gO().q.q, var1.Dw.gO().RF, var6};
               return;
            case WI:
               var3.toString() + "operator %s\n";
               Object[] var73 = new Object[]{var1.Dw.RF().q.RF};
               return;
            case Tq:
               var3.toString() + "extended operator with %d args\n";
               Object[] var72 = new Object[]{var1.Dw.xK().q};
               this.q(var1.Dw.xK().RF, var2 + 2);
               return;
            case RF:
               var3.toString() + "qualified name\n";
               Object[] var71 = new Object[0];
               break;
            case xK:
               var3.toString() + "local name\n";
               Object[] var70 = new Object[0];
               break;
            case Dw:
               var3.toString() + "typed name\n";
               Object[] var69 = new Object[0];
               break;
            case Uv:
               var3.toString() + "template\n";
               Object[] var68 = new Object[0];
               break;
            case za:
               var3.toString() + "vtable\n";
               Object[] var67 = new Object[0];
               break;
            case lm:
               var3.toString() + "VTT\n";
               Object[] var66 = new Object[0];
               break;
            case zz:
               var3.toString() + "construction vtable\n";
               Object[] var65 = new Object[0];
               break;
            case JY:
               var3.toString() + "typeinfo\n";
               Object[] var64 = new Object[0];
               break;
            case HF:
               var3.toString() + "typeinfo name\n";
               Object[] var63 = new Object[0];
               break;
            case LK:
               var3.toString() + "typeinfo function\n";
               Object[] var62 = new Object[0];
               break;
            case io:
               var3.toString() + "thunk\n";
               Object[] var61 = new Object[0];
               break;
            case qa:
               var3.toString() + "virtual thunk\n";
               Object[] var60 = new Object[0];
               break;
            case Hk:
               var3.toString() + "covariant thunk\n";
               Object[] var59 = new Object[0];
               break;
            case Me:
               var3.toString() + "java class\n";
               Object[] var58 = new Object[0];
               break;
            case PV:
               var3.toString() + "guard\n";
               Object[] var57 = new Object[0];
               break;
            case KT:
               var3.toString() + "reference temporary\n";
               Object[] var56 = new Object[0];
               break;
            case Gf:
               var3.toString() + "hidden alias\n";
               Object[] var55 = new Object[0];
               break;
            case Yw:
               var3.toString() + "transaction clone\n";
               Object[] var54 = new Object[0];
               break;
            case IY:
               var3.toString() + "non-transaction clone\n";
               Object[] var53 = new Object[0];
               break;
            case cC:
               var3.toString() + "restrict\n";
               Object[] var52 = new Object[0];
               break;
            case sH:
               var3.toString() + "volatile\n";
               Object[] var51 = new Object[0];
               break;
            case CE:
               var3.toString() + "const\n";
               Object[] var50 = new Object[0];
               break;
            case wF:
               var3.toString() + "restrict this\n";
               Object[] var49 = new Object[0];
               break;
            case If:
               var3.toString() + "volatile this\n";
               Object[] var48 = new Object[0];
               break;
            case Dz:
               var3.toString() + "const this\n";
               Object[] var47 = new Object[0];
               break;
            case mI:
               var3.toString() + "reference this\n";
               Object[] var46 = new Object[0];
               break;
            case jq:
               var3.toString() + "rvalue reference this\n";
               Object[] var45 = new Object[0];
               break;
            case ui:
               var3.toString() + "explicit object parameter\n";
               Object[] var44 = new Object[0];
               break;
            case fw:
               var3.toString() + "transaction_safe this\n";
               Object[] var43 = new Object[0];
               break;
            case TX:
               var3.toString() + "vendor type qualifier\n";
               Object[] var42 = new Object[0];
               break;
            case Rr:
               var3.toString() + "pointer\n";
               Object[] var41 = new Object[0];
               break;
            case EB:
               var3.toString() + "reference\n";
               Object[] var40 = new Object[0];
               break;
            case Xo:
               var3.toString() + "rvalue reference\n";
               Object[] var39 = new Object[0];
               break;
            case Bu:
               var3.toString() + "complex\n";
               Object[] var38 = new Object[0];
               break;
            case IN:
               var3.toString() + "imaginary\n";
               Object[] var37 = new Object[0];
               break;
            case eJ:
               var3.toString() + "vendor type\n";
               Object[] var36 = new Object[0];
               break;
            case YN:
               var3.toString() + "function type\n";
               Object[] var35 = new Object[0];
               break;
            case Rv:
               var3.toString() + "array type\n";
               Object[] var34 = new Object[0];
               break;
            case zx:
               var3.toString() + "pointer to member type\n";
               Object[] var33 = new Object[0];
               break;
            case ZT:
               var3.toString() + "fixed-point type, accum? %d, sat? %d\n";
               Object[] var32 = new Object[]{var1.Dw.JY().RF, var1.Dw.JY().xK};
               this.q(var1.Dw.JY().q, var2 + 2);
               break;
            case GY:
               var3.toString() + "argument list\n";
               Object[] var31 = new Object[0];
               break;
            case Wx:
               var3.toString() + "template argument list\n";
               Object[] var30 = new Object[0];
               break;
            case CY:
               var3.toString() + "initializer list\n";
               Object[] var29 = new Object[0];
               break;
            case Yp:
               var3.toString() + "cast\n";
               Object[] var28 = new Object[0];
               break;
            case Gu:
               var3.toString() + "conversion operator\n";
               Object[] var27 = new Object[0];
               break;
            case nY:
               var3.toString() + "nullary operator\n";
               Object[] var26 = new Object[0];
               break;
            case lF:
               var3.toString() + "unary operator\n";
               Object[] var25 = new Object[0];
               break;
            case nq:
               var3.toString() + "binary operator\n";
               Object[] var24 = new Object[0];
               break;
            case NX:
               var3.toString() + "binary operator arguments\n";
               Object[] var23 = new Object[0];
               break;
            case br:
               var3.toString() + "trinary operator\n";
               Object[] var22 = new Object[0];
               break;
            case tW:
               var3.toString() + "trinary operator arguments 1\n";
               Object[] var21 = new Object[0];
               break;
            case ZA:
               var3.toString() + "trinary operator arguments 1\n";
               Object[] var20 = new Object[0];
               break;
            case Ov:
               var3.toString() + "literal\n";
               Object[] var19 = new Object[0];
               break;
            case Lj:
               var3.toString() + "negative literal\n";
               Object[] var18 = new Object[0];
               break;
            case nv:
               var3.toString() + "vendor expression\n";
               Object[] var17 = new Object[0];
               break;
            case LL:
               var3.toString() + "java resource\n";
               Object[] var16 = new Object[0];
               break;
            case PQ:
               var3.toString() + "compound name\n";
               Object[] var15 = new Object[0];
               break;
            case fQ:
               var3.toString() + "character '%c'\n";
               Object[] var14 = new Object[]{var1.Dw.za().q};
               return;
            case fi:
               var3.toString() + "number %d\n";
               Object[] var13 = new Object[]{var1.Dw.gP().q};
               return;
            case bl:
               var3.toString() + "decltype\n";
               Object[] var12 = new Object[0];
               break;
            case qR:
               var3.toString() + "pack expansion\n";
               Object[] var11 = new Object[0];
               break;
            case oQ:
               var3.toString() + "tls init function\n";
               Object[] var10 = new Object[0];
               break;
            case xW:
               var3.toString() + "tls wrapper function\n";
               Object[] var9 = new Object[0];
               break;
            case GM:
               var3.toString() + "default argument %d\n";
               Object[] var8 = new Object[]{var1.Dw.zz().RF};
               this.q(var1.Dw.zz().q, var2 + 2);
               return;
            case kf:
               var3.toString() + "lambda %d\n";
               Object[] var7 = new Object[]{var1.Dw.zz().RF};
               this.q(var1.Dw.zz().q, var2 + 2);
               return;
            default:
               var3.toString() + "unknown type!!";
               Object[] var10000 = new Object[0];
         }

         try {
            this.q(var1.q(), var2 + 2);
            this.q(var1.RF(), var2 + 2);
         } catch (ClassCastException var5) {
         }
      }
   }

   private boolean q(azb var1, String var2, int var3) {
      if (var1 != null && var2 != null && var3 > 0) {
         var1.RF = 0;
         var1.xK = 0;
         var1.q = azd.q;
         var1.Dw = new azb.qV();
         var1.Dw.q().q = !var2.isEmpty() ? var2.substring(0, var3) : var2;
         return true;
      } else {
         return false;
      }
   }

   private boolean q(azb var1, int var2, azb var3) {
      if (var1 != null && var2 >= 0 && var3 != null) {
         var1.RF = 0;
         var1.xK = 0;
         var1.q = azd.Tq;
         var1.Dw = new azb.tw();
         var1.Dw.xK().q = var2;
         var1.Dw.xK().RF = var3;
         return true;
      } else {
         return false;
      }
   }

   private boolean q(azb var1, azb.Bu var2, azb var3) {
      if (var1 != null && var3 != null) {
         var1.RF = 0;
         var1.xK = 0;
         var1.q = azd.nf;
         var1.Dw = new azb.oM();
         var1.Dw.Dw().q = var2;
         var1.Dw.Dw().RF = var3;
         return true;
      } else {
         return false;
      }
   }

   private boolean q(azb var1, azb.bK var2, azb var3) {
      if (var1 != null && var3 != null) {
         var1.RF = 0;
         var1.xK = 0;
         var1.q = azd.gP;
         var1.Dw = new azb.Nt();
         var1.Dw.Uv().q = var2;
         var1.Dw.Uv().RF = var3;
         return true;
      } else {
         return false;
      }
   }

   private azb q(ayw var1) {
      azb var2 = new azb();
      var2.RF = 0;
      var2.xK = 0;
      var1.Uv.add(var2);
      return var2;
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   private azb q(ayw var1, azd var2, azb var3, azb var4) {
      switch (var2) {
         case YA:
         case RF:
         case xK:
         case Dw:
         case Uv:
         case zz:
         case TX:
         case zx:
         case lF:
         case nq:
         case NX:
         case br:
         case tW:
         case Ov:
         case Lj:
         case nv:
         case PQ:
         case Ri:
         case Wp:
         case jh:
         case mJ:
            if (var3 == null || var4 == null) {
               return null;
            }
            break;
         case oW:
         case gO:
         case nf:
         case gP:
         case Ef:
         case rL:
         case Bs:
         case WI:
         case Tq:
         case ZT:
         case fQ:
         case fi:
         case GM:
         case kf:
         default:
            return null;
         case AB:
         case za:
         case lm:
         case JY:
         case HF:
         case LK:
         case io:
         case qa:
         case Hk:
         case Me:
         case PV:
         case KT:
         case Gf:
         case Yw:
         case IY:
         case Rr:
         case EB:
         case Xo:
         case Bu:
         case IN:
         case eJ:
         case Yp:
         case Gu:
         case nY:
         case ZA:
         case LL:
         case bl:
         case qR:
         case oQ:
         case xW:
         case jb:
         case pQ:
         case eC:
         case Jf:
         case vC:
         case os:
         case iu:
         case fn:
         case cY:
         case ZU:
         case Sz:
         case fq:
            if (var3 == null) {
               return null;
            }
         case cC:
         case sH:
         case CE:
         case wF:
         case If:
         case Dz:
         case mI:
         case jq:
         case ui:
         case fw:
         case YN:
         case GY:
         case Wx:
         case of:
         case PY:
         case cR:
            break;
         case Rv:
         case CY:
         case ND:
         case Qu:
            if (var4 == null) {
               return null;
            }
      }

      azb var5 = this.q(var1);
      if (var5 != null) {
         var5.q = var2;
         var5.Dw = new azb.CU();
         var5.Dw.lm().q = var3;
         var5.Dw.lm().RF = var4;
      }

      return var5;
   }

   private azb q(ayw var1, String var2, int var3) {
      azb var4 = this.q(var1);
      return !this.q(var4, var2, var3) ? null : var4;
   }

   private azb q(ayw var1, aza var2) {
      if (var2 == null) {
         return null;
      } else {
         azb var3 = this.q(var1);
         if (var3 != null) {
            var3.q = azd.rL;
            var3.Dw = new azb.nI();
            var3.Dw.oW().q = var2;
         }

         return var3;
      }
   }

   private azb q(ayw var1, aza var2, int var3, char var4) {
      return this.q(var1, var2, new ayz((short)var3), var4);
   }

   private azb q(ayw var1, aza var2, ayz var3, char var4) {
      if (var2 == null) {
         return null;
      } else {
         azb var5 = this.q(var1);
         if (var5 != null) {
            var5.q = azd.Bs;
            var5.Dw = new azb.iZ();
            var5.Dw.gO().q = var2;
            var5.Dw.gO().RF = var3;
            var5.Dw.gO().Dw = var4;
         }

         return var5;
      }
   }

   private azb q(ayw var1, aze var2) {
      azb var3 = this.q(var1);
      if (var3 != null) {
         var3.q = azd.WI;
         var3.Dw = new azb.PY();
         var3.Dw.RF().q = var2;
      }

      return var3;
   }

   private azb q(ayw var1, int var2, azb var3) {
      azb var4 = this.q(var1);
      return !this.q(var4, var2, var3) ? null : var4;
   }

   private azb RF(ayw var1, int var2, azb var3) {
      azb var4 = this.q(var1);
      if (var4 != null) {
         var4.q = azd.GM;
         var4.Dw = new azb.oL();
         var4.Dw.zz().RF = var2;
         var4.Dw.zz().q = var3;
      }

      return var4;
   }

   private azb q(ayw var1, azb.Bu var2, azb var3) {
      azb var4 = this.q(var1);
      return !this.q(var4, var2, var3) ? null : var4;
   }

   private azb q(ayw var1, azb.bK var2, azb var3) {
      azb var4 = this.q(var1);
      return !this.q(var4, var2, var3) ? null : var4;
   }

   private azb q(ayw var1, int var2) {
      azb var3 = this.q(var1);
      if (var3 != null) {
         var3.q = azd.oW;
         var3.Dw = new azb.vn();
         var3.Dw.gP().q = var2;
      }

      return var3;
   }

   private azb RF(ayw var1, int var2) {
      azb var3 = this.q(var1);
      if (var3 != null) {
         var3.q = azd.gO;
         var3.Dw = new azb.vn();
         var3.Dw.gP().q = var2;
      }

      return var3;
   }

   private azb q(ayw var1, String var2) {
      azb var3 = this.q(var1);
      if (var3 != null) {
         var3.q = azd.Ef;
         var3.Dw = new azb.vb();
         var3.Dw.nf().q = var2;
      }

      return var3;
   }

   public azb q(ayw var1, boolean var2) {
      if (!var1.q('_') && var2) {
         return null;
      } else if (!var1.q('Z')) {
         return null;
      } else {
         azb var3 = this.RF(var1, var2);
         if (var2) {
            while (var1.q() == '.' && (Character.isLowerCase(var1.RF()) || var1.RF() == '_' || Character.isDigit(var1.RF()))) {
               var3 = this.Dw(var1, var3);
            }
         }

         return var3;
      }
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   private boolean RF(azb var1) {
      if (var1 == null) {
         return false;
      } else {
         switch (var1.q) {
            case xK:
               return this.RF(var1.RF());
            case Uv:
               return !this.xK(var1.q());
            case wF:
            case If:
            case Dz:
            case mI:
            case jq:
            case ui:
            case fw:
            case PY:
            case cR:
               return this.RF(var1.q());
            default:
               return false;
         }
      }
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   private boolean xK(azb var1) {
      if (var1 == null) {
         return false;
      } else {
         switch (var1.q) {
            case nf:
            case gP:
            case Gu:
               return true;
            case RF:
            case xK:
               return this.xK(var1.RF());
            default:
               return false;
         }
      }
   }

   private azb q(ayw var1, azb var2) {
      if (var1.q() == 'Q') {
         var1.q(1);
         azb var3 = this.KT(var1);
         if (var3 == null) {
            return null;
         }

         var2 = this.q(var1, azd.mJ, var2, var3);
      }

      return var2;
   }

   private azb RF(ayw var1, boolean var2) {
      char var3 = var1.q();
      if (var3 != 'G' && var3 != 'T') {
         azb var4 = this.xK(var1, false);
         var3 = var1.q();
         if (var4 != null && var3 != 0 && var3 != 'E' && var3 != '.') {
            azb var5 = this.oW(var1, this.RF(var4));
            if (var5 == null) {
               return null;
            } else {
               if (!var2 && var4.q == azd.xK && var5.q == azd.YN) {
                  var5.Dw.lm().q = null;
               }

               var5 = this.q(var1, var5);
               return this.q(var1, azd.Dw, var4, var5);
            }
         } else {
            return var4;
         }
      } else {
         return this.gO(var1);
      }
   }

   private azb RF(ayw var1, azb var2) {
      azb var3 = var1.gO;
      char var4 = var1.q();
      int var5 = 0;

      while (var4 == 'B') {
         var1.q(1);
         azb var6 = this.xK(var1);
         var2 = this.q(var1, azd.YA, var2, var6);
         var4 = var1.q();
         if (++var5 > 1000) {
            return null;
         }
      }

      var1.gO = var3;
      return var2;
   }

   private azb xK(ayw var1, boolean var2) {
      char var3 = var1.q();
      boolean var4 = false;
      azb var5 = null;
      azb var6 = null;
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
            var5 = this.q(var1, var5, var6);
            if (var1.q() == 'I') {
               var1.q(var5);
               var5 = this.q(var1, azd.Uv, var5, this.Hk(var1));
            }
            break;
         case 'N':
            var5 = this.RF(var1);
            break;
         case 'S':
            if (var1.RF() == 't') {
               var1.q(2);
               var5 = this.q(var1, "std", 3);
               var1.nf += 3;
            }

            if (var1.q() == 'S') {
               var6 = this.Uv(var1, 0);
               if (var6 == null) {
                  return null;
               }

               if (var6.q != azd.ND && var6.q != azd.Qu) {
                  if (var5 != null) {
                     return null;
                  }

                  var4 = true;
                  var5 = var6;
                  var6 = null;
               }
            }

            if (!var4) {
               var5 = this.q(var1, var5, var6);
            }

            if (var1.q() == 'I') {
               if (!var4) {
                  var1.q(var5);
               }

               var5 = this.q(var1, azd.Uv, var5, this.Hk(var1));
               var4 = false;
            }
            break;
         case 'U':
            var5 = this.q(var1, null, null);
            break;
         case 'Z':
            var5 = this.Ef(var1);
      }

      if (var2 && !var4) {
         var1.q(var5);
      }

      return var5;
   }

   private azb RF(ayw var1) {
      azc var2 = new azc(null);
      new azc(null);
      if (!var1.q('N')) {
         return null;
      } else {
         azc var3;
         azb var4;
         if (var1.q() == 'H') {
            var1.q(1);
            var1.nf = var1.nf + "this".length();
            var3 = var2;
            var4 = this.q(var1, azd.ui, null, null);
         } else {
            var3 = this.q(var1, var2, true, false);
            if (var3 == null) {
               return null;
            }

            var4 = this.xK(var1, null);
         }

         var3.RF(this.Dw(var1, true));
         if (var3.RF() == null) {
            return null;
         } else {
            if (var4 != null) {
               var4.Dw.lm().q = var2.q();
               var2.q(var4);
            }

            return !var1.q('E') ? null : var2.q();
         }
      }
   }

   private azb Dw(ayw var1, boolean var2) {
      azb var3 = null;
      int var4 = 0;

      while (true) {
         while (true) {
            char var5 = var1.q();
            if (var5 == 'D' && (var1.RF() == 'T' || var1.RF() == 't')) {
               if (var3 != null) {
                  return null;
               }

               var3 = this.za(var1);
               break;
            }

            if (var5 == 'I') {
               if (var3 == null) {
                  return null;
               }

               azb var7 = this.Hk(var1);
               if (var7 == null) {
                  return null;
               }

               var3 = this.q(var1, azd.Uv, var3, var7);
               break;
            }

            if (var5 == 'T') {
               if (var3 != null) {
                  return null;
               }

               var3 = this.qa(var1);
               break;
            }

            if (var5 == 'M') {
               var1.q(1);
            } else {
               azb var6 = null;
               if (var5 == 'S') {
                  var6 = this.Uv(var1, 1);
                  if (var6 == null) {
                     return null;
                  }

                  if (var6.q != azd.ND && var6.q != azd.Qu) {
                     if (var3 != null) {
                        return null;
                     }

                     var3 = var6;
                     continue;
                  }
               }

               var3 = this.q(var1, var3, var6);
               break;
            }
         }

         if (var3 == null || var1.q() == 'E') {
            return var3;
         }

         if (var2) {
            var1.q(var3);
         }

         if (++var4 > 1000) {
            return null;
         }
      }
   }

   private boolean q(ayw var1, azc var2) {
      while (var1.q() == 'W') {
         var1.q(1);
         azd var3 = azd.ND;
         if (var1.q() == 'P') {
            var3 = azd.Qu;
            var1.q(1);
         }

         var2.RF(this.q(var1, var3, var2.q(), this.xK(var1)));
         if (var2.RF() == null) {
            return false;
         }

         if (!var1.q(var2.RF())) {
            return false;
         }
      }

      return true;
   }

   private azb q(ayw var1, azb var2, azb var3) {
      azb var4 = null;
      boolean var5 = false;
      azc var6 = new azc(var3);
      if (!this.q(var1, var6)) {
         return null;
      } else {
         var3 = var6.q();
         char var7 = var1.q();
         if (var7 == 'F') {
            var5 = true;
            var1.q(1);
            var7 = var1.q();
         }

         if (Character.isDigit(var7)) {
            var4 = this.xK(var1);
         } else if (Character.isLowerCase(var7)) {
            boolean var8 = var1.gP;
            if (var7 == 'o' && var1.RF() == 'n') {
               var1.q(2);
               var1.gP = false;
            }

            var4 = this.oW(var1);
            var1.gP = var8;
            if (var4 != null && var4.q == azd.WI) {
               var1.nf = var1.nf + ("operator".length() + var4.Dw.RF().q.RF.length() - 2);
               if (var4.Dw.RF().q.q.equals("li")) {
                  var4 = this.q(var1, azd.lF, var4, this.xK(var1));
               }
            }
         } else if (var7 == 'D' && var1.RF() == 'C') {
            var1.q(2);
            azb var11 = null;

            azb var9;
            do {
               var9 = this.q(var1, azd.eC, this.xK(var1), null);
               if (var11 != null) {
                  var11.Dw.lm().RF = var9;
               } else {
                  var4 = var9;
               }

               var11 = var9;
            } while (var9 != null && var1.q() != 'E');

            if (var9 != null) {
               var1.q(1);
            } else {
               var4 = null;
            }
         } else if (var7 == 'C' || var7 == 'D') {
            var4 = this.nf(var1);
         } else if (var7 == 'L') {
            var1.q(1);
            var4 = this.xK(var1);
            if (var4 == null) {
               return null;
            }

            if (!this.cC(var1)) {
               return null;
            }
         } else {
            if (var7 != 'U') {
               return null;
            }

            switch (var1.RF()) {
               case 'l':
                  var4 = this.sH(var1);
                  break;
               case 't':
                  var4 = this.CE(var1);
                  break;
               default:
                  return null;
            }
         }

         if (var3 != null) {
            var4 = this.q(var1, azd.jh, var4, var3);
         }

         if (var1.q() == 'B') {
            var4 = this.RF(var1, var4);
         }

         if (var5) {
            var4 = this.q(var1, azd.cY, var4, null);
         }

         if (var2 != null) {
            var4 = this.q(var1, azd.RF, var2, var4);
         }

         return var4;
      }
   }

   private azb xK(ayw var1) {
      int var3 = this.Dw(var1);
      if (var3 <= 0) {
         return null;
      } else {
         azb var2 = this.xK(var1, var3);
         var1.gO = var2;
         return var2;
      }
   }

   private int Dw(ayw var1) {
      boolean var2 = false;
      int var3 = 0;
      char var4 = var1.q();
      if (var4 == 'n') {
         var2 = true;
         var1.q(1);
         var4 = var1.q();
      }

      int var5 = 0;

      while (Character.isDigit(var4)) {
         if (var3 > (Integer.MAX_VALUE - (var4 - '0')) / 10) {
            return -1;
         }

         var3 = var3 * 10 + var4 - 48;
         var1.q(1);
         var4 = var1.q();
         if (++var5 > 1000) {
            return -1;
         }
      }

      if (var2) {
         var3 = -var3;
      }

      return var3;
   }

   private azb Uv(ayw var1) {
      azb var2 = this.q(var1);
      var2.q = azd.fi;
      var2.Dw = new azb.vn();
      var2.Dw.gP().q = this.Dw(var1);
      return var2;
   }

   private azb xK(ayw var1, int var2) {
      String var3 = var1.Dw();
      if (!var3.isEmpty() && var3.length() < var2) {
         return null;
      } else {
         var1.q(var2);
         if (var2 >= xK + 2
            && var3.startsWith("_GLOBAL_")
            && (var3.charAt(xK) == '.' || var3.charAt(xK) == '_' || var3.charAt(xK) == '$')
            && var3.charAt(xK + 1) == 'N') {
            var1.nf = var1.nf - (var2 - "(anonymous namespace)".length() + 1);
            return this.q(var1, "(anonymous namespace)", "(anonymous namespace)".length());
         } else {
            return this.q(var1, var3, var2);
         }
      }
   }

   private azb oW(ayw var1) {
      char var2 = var1.xK();
      char var3 = var1.xK();
      if (var2 == 'v' && Character.isDigit(var3)) {
         return this.q(var1, var3 - '0', this.xK(var1));
      } else if (var2 == 'c' && var3 == 'v') {
         boolean var7 = var1.za;
         var1.za = !var1.gP;
         azb var5 = this.za(var1);
         azb var6;
         if (var1.za) {
            var6 = this.q(var1, azd.Gu, var5, null);
         } else {
            var6 = this.q(var1, azd.Yp, var5, null);
         }

         var1.za = var7;
         return var6;
      } else {
         aze var4 = aze.q("" + var2 + var3);
         return var4 == null ? null : this.q(var1, var4);
      }
   }

   private azb q(ayw var1, char var2) {
      azb var3 = this.q(var1);
      if (var3 != null) {
         var3.q = azd.fQ;
         var3.Dw = new azb.ej();
         var3.Dw.za().q = var2;
      }

      return var3;
   }

   private azb gO(ayw var1) {
      var1.nf += 20;
      if (var1.q('T')) {
         switch (var1.xK()) {
            case 'A':
               return this.q(var1, azd.AB, this.PV(var1), null);
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
               azb var5 = this.za(var1);
               int var6 = this.Dw(var1);
               if (var6 < 0) {
                  return null;
               } else {
                  if (!var1.q('_')) {
                     return null;
                  }

                  azb var4 = this.za(var1);
                  var1.nf += 5;
                  return this.q(var1, azd.zz, var4, var5);
               }
            case 'F':
               return this.q(var1, azd.LK, this.za(var1), null);
            case 'H':
               return this.q(var1, azd.oQ, this.xK(var1, false), null);
            case 'I':
               return this.q(var1, azd.JY, this.za(var1), null);
            case 'J':
               return this.q(var1, azd.Me, this.za(var1), null);
            case 'S':
               return this.q(var1, azd.HF, this.za(var1), null);
            case 'T':
               var1.nf -= 10;
               return this.q(var1, azd.lm, this.za(var1), null);
            case 'V':
               var1.nf -= 5;
               return this.q(var1, azd.za, this.za(var1), null);
            case 'W':
               return this.q(var1, azd.xW, this.xK(var1, false), null);
            case 'c':
               if (!this.Dw(var1, 0)) {
                  return null;
               } else {
                  if (!this.Dw(var1, 0)) {
                     return null;
                  }

                  return this.q(var1, azd.Hk, this.RF(var1, false), null);
               }
            case 'h':
               if (!this.Dw(var1, 104)) {
                  return null;
               }

               return this.q(var1, azd.io, this.RF(var1, false), null);
            case 'v':
               return !this.Dw(var1, 118) ? null : this.q(var1, azd.qa, this.RF(var1, false), null);
         }
      } else if (var1.q('G')) {
         switch (var1.xK()) {
            case 'A':
               return this.q(var1, azd.Gf, this.RF(var1, false), null);
            case 'I':
               azc var3 = new azc(null);
               if (!this.q(var1, var3)) {
                  return null;
               } else {
                  if (var3.q() == null) {
                     return null;
                  }

                  return this.q(var1, azd.Jf, var3.q(), null);
               }
            case 'R':
               azb var2 = this.q(var1, azd.KT, this.xK(var1, false), this.Uv(var1));
               if (var1.q() == '_') {
                  var1.q(1);
               }

               return var2;
            case 'T':
               switch (var1.xK()) {
                  case 'n':
                     return this.q(var1, azd.IY, this.RF(var1, false), null);
                  case 't':
                  default:
                     return this.q(var1, azd.Yw, this.RF(var1, false), null);
               }
            case 'V':
               return this.q(var1, azd.PV, this.xK(var1, false), null);
            case 'r':
               throw new RuntimeException("TBI: java resource");
            default:
               return null;
         }
      } else {
         return null;
      }
   }

   private boolean Dw(ayw var1, int var2) {
      if (var2 == 0) {
         var2 = var1.xK();
      }

      if (var2 == 104) {
         this.Dw(var1);
      } else {
         if (var2 != 118) {
            return false;
         }

         this.Dw(var1);
         if (!var1.q('_')) {
            return false;
         }

         this.Dw(var1);
      }

      return var1.q('_');
   }

   private azb nf(ayw var1) {
      if (var1.gO != null) {
         if (var1.gO.q == azd.q) {
            var1.nf = var1.nf + var1.gO.Dw.q().q.length();
         } else if (var1.gO.q == azd.Ef) {
            var1.nf = var1.nf + var1.gO.Dw.nf().q.length();
         }
      }

      switch (var1.q()) {
         case 'C':
            boolean var3 = false;
            if (var1.RF() == 'I') {
               var3 = true;
               var1.q(1);
            }

            azb.Bu var4;
            switch (var1.RF()) {
               case '1':
                  var4 = azb.Bu.q;
                  break;
               case '2':
                  var4 = azb.Bu.RF;
                  break;
               case '3':
                  var4 = azb.Bu.xK;
                  break;
               case '4':
                  var4 = azb.Bu.Dw;
                  break;
               case '5':
                  var4 = azb.Bu.Uv;
                  break;
               default:
                  return null;
            }

            var1.q(2);
            if (var3) {
               this.za(var1);
            }

            return this.q(var1, var4, var1.gO);
         case 'D':
            azb.bK var2;
            switch (var1.RF()) {
               case '0':
                  var2 = azb.bK.q;
                  break;
               case '1':
                  var2 = azb.bK.RF;
                  break;
               case '2':
                  var2 = azb.bK.xK;
                  break;
               case '3':
               default:
                  return null;
               case '4':
                  var2 = azb.bK.Dw;
                  break;
               case '5':
                  var2 = azb.bK.Uv;
            }

            var1.q(2);
            return this.q(var1, var2, var1.gO);
         default:
            return null;
      }
   }

   private boolean gP(ayw var1) {
      char var2 = var1.q();
      if (var2 != 'r' && var2 != 'V' && var2 != 'K') {
         if (var2 == 'D') {
            var2 = var1.RF();
            if (var2 == 'x' || var2 == 'o' || var2 == 'O' || var2 == 'w') {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   private azb za(ayw var1) {
      return this.Uv(var1, true);
   }

   private azb Uv(ayw var1, boolean var2) {
      if (this.gP(var1)) {
         new azc(null);
         azc var19 = new azc(null);
         azc var18 = this.q(var1, var19, false, true);
         if (var18 == null) {
            return null;
         } else {
            if (var1.q() == 'F') {
               var18.RF(this.lm(var1));
            } else {
               var18.RF(this.za(var1));
            }

            if (var18.RF() == null) {
               return null;
            } else {
               if (var18.RF().q == azd.jq || var18.RF().q == azd.mI) {
                  azb var20 = var18.RF().q();
                  var18.RF().Dw.lm().q = var19.q();
                  var19 = new azc(var18.RF());
                  var18.RF(var20);
               }

               if (var2) {
                  var1.q(var19.q());
               }

               return var19.q();
            }
         }
      } else {
         azb var4;
         var2 = true;
         char var3 = var1.q();
         label152:
         switch (var3) {
            case 'A':
               var4 = this.JY(var1);
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
               return this.gO(var1, true);
            case 'C':
               var1.q(1);
               var4 = this.q(var1, azd.Bu, this.za(var1), null);
               break;
            case 'D':
               var2 = false;
               var1.q(1);
               var3 = var1.xK();
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
                     int var23 = this.Dw(var1);
                     if (var1.q() == 'b') {
                        if (var23 != 16) {
                           return null;
                        }

                        var1.q(1);
                        var4 = this.q(var1, aza.q(35));
                        var1.nf = var1.nf + var4.Dw.oW().q.q.length();
                     } else {
                        char var24 = 0;
                        if (var1.q() == 'x') {
                           var24 = 'x';
                        } else if (var1.q() != '_') {
                           return null;
                        }

                        var4 = this.q(var1, aza.q(34), var23, var24);
                        var1.q(1);
                        String var25 = Integer.toString(var23);
                        var1.nf = var1.nf + var4.Dw.gO().q.q.length() + var25.length() + (var24 == 0 ? 0 : 1);
                     }
                     break label152;
                  case 'S':
                     var22 = true;
                     if (var1.q() != 'D') {
                        return null;
                     }

                     var1.q(1);
                     var3 = var1.q();
                     if (var3 != 'A' && var3 != 'R') {
                        return null;
                     }

                     var1.q(1);
                  case 'A':
                  case 'R':
                     var4 = this.q(var1);
                     var4.q = azd.ZT;
                     var4.Dw = new azb.EE();
                     var4.Dw.JY().RF = var3 == 'A';
                     var4.Dw.JY().xK = var22;
                     var4.Dw.JY().q = this.za(var1);
                     if (var4.Dw.JY().q == null) {
                        return null;
                     }
                     break label152;
                  case 'T':
                  case 't':
                     var4 = this.q(var1, azd.bl, this.KT(var1), null);
                     if (var4 != null && var1.xK() != 'E') {
                        var4 = null;
                     }

                     var2 = true;
                     break label152;
                  case 'U':
                     var21 = 1;
                  case 'B':
                     int var10 = var1.xK;
                     int var11 = this.Dw(var1);
                     int var13 = 2;
                     ayz var12;
                     if (var10 == var1.xK) {
                        azb var14 = this.KT(var1);
                        if (var14 == null) {
                           return null;
                        }

                        var12 = new ayz(var14);
                     } else {
                        var12 = new ayz((short)var11);
                        String var26 = Integer.toString(var11);
                        var13 += var26.length();
                     }

                     if (var1.q() != '_') {
                        return null;
                     }

                     var4 = this.q(var1, aza.q(36 + var21), var12, '\u0000');
                     var4.Dw.gO().xK = true;
                     var1.q(1);
                     var1.nf = var1.nf + var4.Dw.gO().q.q.length() + var13;
                     break label152;
                  case 'a':
                     var4 = this.q(var1, "auto", 4);
                     break label152;
                  case 'c':
                     var4 = this.q(var1, "decltype(auto)", 14);
                     break label152;
                  case 'd':
                     var4 = this.q(var1, aza.q(27));
                     var1.nf = var1.nf + var4.Dw.oW().q.q.length();
                     break label152;
                  case 'e':
                     var4 = this.q(var1, aza.q(28));
                     var1.nf = var1.nf + var4.Dw.oW().q.q.length();
                     break label152;
                  case 'f':
                     var4 = this.q(var1, aza.q(26));
                     var1.nf = var1.nf + var4.Dw.oW().q.q.length();
                     break label152;
                  case 'h':
                     var4 = this.q(var1, aza.q(29));
                     var1.nf = var1.nf + var4.Dw.oW().q.q.length();
                     break label152;
                  case 'i':
                     var4 = this.q(var1, aza.q(32));
                     var1.nf = var1.nf + var4.Dw.oW().q.q.length();
                     break label152;
                  case 'n':
                     var4 = this.q(var1, aza.q(33));
                     var1.nf = var1.nf + var4.Dw.oW().q.q.length();
                     break label152;
                  case 'p':
                     var4 = this.q(var1, azd.qR, this.za(var1), null);
                     var2 = true;
                     break label152;
                  case 's':
                     var4 = this.q(var1, aza.q(31));
                     var1.nf = var1.nf + var4.Dw.oW().q.q.length();
                     break label152;
                  case 'u':
                     var4 = this.q(var1, aza.q(30));
                     var1.nf = var1.nf + var4.Dw.oW().q.q.length();
                     break label152;
                  case 'v':
                     var4 = this.HF(var1);
                     var2 = true;
                     break label152;
               }
            case 'F':
               var4 = this.lm(var1);
               break;
            case 'G':
               var1.q(1);
               var4 = this.q(var1, azd.IN, this.za(var1), null);
               break;
            case 'M':
               var4 = this.LK(var1);
               break;
            case 'O':
               var1.q(1);
               var4 = this.q(var1, azd.Xo, this.za(var1), null);
               break;
            case 'P':
               var1.q(1);
               var4 = this.q(var1, azd.Rr, this.za(var1), null);
               break;
            case 'R':
               var1.q(1);
               var4 = this.q(var1, azd.EB, this.za(var1), null);
               break;
            case 'T':
               char var7 = var1.RF();
               if (var7 == 's' || var7 == 'u' || var7 == 'e') {
                  return this.gO(var1, true);
               }

               var4 = this.qa(var1);
               if (var1.q() == 'I') {
                  if (!var1.za) {
                     var1.q(var4);
                     var4 = this.q(var1, azd.Uv, var4, this.Hk(var1));
                  } else {
                     ayw var8 = new ayw(var1);
                     azb var9 = this.Hk(var1);
                     if (var1.q() == 'I') {
                        var1.q(var4);
                        var4 = this.q(var1, azd.Uv, var4, var9);
                     } else {
                        var1.xK = var8.xK;
                        var1.Uv = var8.Uv;
                        var1.oW = var8.oW;
                        var1.nf = var8.nf;
                     }
                  }
               }
               break;
            case 'U':
               var1.q(1);
               var4 = this.xK(var1);
               if (var1.q() == 'I') {
                  var4 = this.q(var1, azd.Uv, var4, this.Hk(var1));
               }

               var4 = this.q(var1, azd.TX, this.Uv(var1, false), var4);
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
               var4 = this.q(var1, aza.q(var3 - 'a'));
               var1.nf = var1.nf + var4.Dw.oW().q.q.length();
               var2 = false;
               var1.q(1);
               break;
            case 'u':
               var1.q(1);
               azb var5 = this.xK(var1);
               azb var6 = this.Hk(var1);
               var4 = this.q(var1, azd.eJ, var5, var6);
         }

         if (var2) {
            var1.q(var4);
         }

         return var4;
      }
   }

   private azc q(ayw var1, azc var2, boolean var3, boolean var4) {
      azc var5 = var2;
      char var6 = var1.q();
      int var7 = 0;

      while (this.gP(var1)) {
         azb var9 = null;
         var1.q(1);
         azd var8;
         if (var6 == 'r') {
            var8 = var3 ? azd.wF : azd.cC;
            var1.nf = var1.nf + "restrict".length();
         } else if (var6 == 'V') {
            var8 = var3 ? azd.If : azd.sH;
            var1.nf = var1.nf + "volatile".length();
         } else if (var6 == 'K') {
            var8 = var3 ? azd.Dz : azd.CE;
            var1.nf = var1.nf + "const".length();
         } else {
            if (!var4) {
               break;
            }

            var6 = var1.xK();
            if (var6 == 'x') {
               var8 = azd.fw;
               var1.nf = var1.nf + "transaction_safe".length();
            } else if (var6 != 'o' && var6 != 'O') {
               if (var6 != 'w') {
                  return null;
               }

               var8 = azd.cR;
               var1.nf = var1.nf + "throw".length();
               var9 = this.zz(var1);
               if (var9 == null) {
                  return null;
               }

               if (!var1.q('E')) {
                  return null;
               }
            } else {
               var8 = azd.PY;
               var1.nf = var1.nf + "noexcept".length();
               if (var6 == 'O') {
                  var9 = this.KT(var1);
                  if (var9 == null) {
                     return null;
                  }

                  if (!var1.q('E')) {
                     return null;
                  }
               }
            }
         }

         var2.RF(this.q(var1, var8, null, var9));
         if (var2.RF() == null) {
            return null;
         }

         azb var10 = var2.RF();
         var2 = new azc(null);
         var2.q(var10, true, false);
         var6 = var1.q();
         if (++var7 > 1000) {
            return null;
         }
      }

      if (!var3 && var6 == 'F') {
         while (var5.RF() != var2.RF()) {
            switch (var5.RF().q) {
               case cC:
                  var5.RF().q = azd.wF;
                  break;
               case sH:
                  var5.RF().q = azd.If;
                  break;
               case CE:
                  var5.RF().q = azd.Dz;
            }

            azb var12 = var5.RF();
            var5 = new azc(null);
            var5.q(var12, true, false);
         }
      }

      return var2;
   }

   private azb xK(ayw var1, azb var2) {
      azb var3 = var2;
      char var4 = var1.q();
      if (var4 == 'R' || var4 == 'O') {
         azd var5;
         if (var4 == 'R') {
            var5 = azd.mI;
            var1.nf = var1.nf + "&".length();
         } else {
            var5 = azd.jq;
            var1.nf = var1.nf + "&&".length();
         }

         var1.q(1);
         var3 = this.q(var1, var5, var2, null);
      }

      return var3;
   }

   private azb lm(ayw var1) {
      if (!var1.q('F')) {
         return null;
      } else {
         if (var1.q() == 'Y') {
            var1.q(1);
         }

         azb var2 = this.oW(var1, true);
         var2 = this.xK(var1, var2);
         return !var1.q('E') ? null : var2;
      }
   }

   private azb zz(ayw var1) {
      azb var2 = null;
      azb var3 = null;
      int var4 = 0;

      do {
         char var6 = var1.q();
         if (var6 == 0 || var6 == 'E' || var6 == '.' || var6 == 'Q' || (var6 == 'R' || var6 == 'O') && var1.RF() == 'E') {
            if (var3 == null) {
               return null;
            } else {
               if (var3.RF() == null && var3.q().q == azd.rL && var3.q().Dw.oW().q.xK == aza.eo.za) {
                  var1.nf = var1.nf - var3.q().Dw.oW().q.q.length();
                  var3.Dw.lm().q = null;
               }

               return var3;
            }
         }

         azb var5 = this.za(var1);
         if (var5 == null) {
            return null;
         }

         if (var2 == null) {
            var2 = this.q(var1, azd.GY, var5, null);
            if (var2 == null) {
               return null;
            }

            var3 = var2;
         } else {
            var2.Dw.lm().RF = this.q(var1, azd.GY, var5, null);
            if (var2.RF() == null) {
               return null;
            }

            var2 = var2.RF();
         }
      } while (++var4 <= 1000);

      return null;
   }

   private azb oW(ayw var1, boolean var2) {
      azb var3 = null;
      char var5 = var1.q();
      if (var5 == 'J') {
         var1.q(1);
         var2 = true;
      }

      if (var2) {
         var3 = this.za(var1);
         if (var3 == null) {
            return null;
         }
      }

      azb var4 = this.zz(var1);
      return var3 == null && var4 == null ? null : this.q(var1, azd.YN, var3, var4);
   }

   private azb gO(ayw var1, boolean var2) {
      azd var3 = null;
      if (var1.q() == 'T') {
         char var4 = var1.RF();
         if (var4 == 's') {
            var3 = azd.ZU;
            var1.q(2);
         } else if (var4 == 'u') {
            var3 = azd.Sz;
            var1.q(2);
         } else if (var4 == 'e') {
            var3 = azd.fq;
            var1.q(2);
         }
      }

      azb var5 = this.xK(var1, var2);
      return var3 != null ? this.q(var1, var3, var5, null) : var5;
   }

   private azb JY(ayw var1) {
      azb var2 = null;
      if (!var1.q('A')) {
         return null;
      } else {
         char var3 = var1.q();
         if (var3 != '_') {
            if (Character.isDigit(var3)) {
               String var4 = var1.Dw();

               do {
                  var1.q(1);
                  var3 = var1.q();
               } while (Character.isDigit(var3));

               int var5 = var4.length() - var1.Dw().length();
               var2 = this.q(var1, var4, var5);
               if (var2 == null) {
                  return null;
               }
            } else {
               var2 = this.KT(var1);
               if (var2 == null) {
                  return null;
               }
            }
         }

         return !var1.q('_') ? null : this.q(var1, azd.Rv, var2, this.za(var1));
      }
   }

   private azb HF(ayw var1) {
      char var3 = var1.q();
      azb var2;
      if (var3 == '_') {
         var1.q(1);
         var2 = this.KT(var1);
      } else {
         var2 = this.Uv(var1);
      }

      if (var2 == null) {
         return null;
      } else {
         return !var1.q('_') ? null : this.q(var1, azd.Ri, var2, this.za(var1));
      }
   }

   private azb LK(ayw var1) {
      if (!var1.q('M')) {
         return null;
      } else {
         azb var2 = this.za(var1);
         if (var2 == null) {
            return null;
         } else {
            azb var3 = this.za(var1);
            return var3 == null ? null : this.q(var1, azd.zx, var2, var3);
         }
      }
   }

   private int io(ayw var1) {
      int var2;
      if (var1.q() == '_') {
         var2 = 0;
      } else {
         if (var1.q() == 'n') {
            return -1;
         }

         var2 = this.Dw(var1) + 1;
      }

      return var2 >= 0 && var1.q((char)95) ? var2 : -1;
   }

   private azb qa(ayw var1) {
      if (!var1.q('T')) {
         return null;
      } else {
         int var2 = this.io(var1);
         return var2 < 0 ? null : this.q(var1, var2);
      }
   }

   private azb Hk(ayw var1) {
      if (var1.q() != 'I' && var1.q() != 'J') {
         return null;
      } else {
         var1.q(1);
         return this.Me(var1);
      }
   }

   private azb Me(ayw var1) {
      azb var2 = var1.gO;
      if (var1.q() == 'E') {
         var1.q(1);
         return this.q(var1, azd.Wx, null, null);
      } else {
         azb var3 = null;
         azb var4 = null;
         int var5 = 0;

         do {
            azb var6 = this.PV(var1);
            if (var6 == null) {
               return null;
            }

            if (var3 == null) {
               var3 = this.q(var1, azd.Wx, var6, null);
               if (var3 == null) {
                  return null;
               }

               var4 = var3;
            } else {
               var4.Dw.lm().RF = this.q(var1, azd.Wx, var6, null);
               if (var4.RF() == null) {
                  return null;
               }

               var4 = var4.RF();
            }

            char var7 = var1.q();
            if (var7 == 'E' || var7 == 'Q') {
               var3 = this.q(var1, var3);
               if (var1.q() != 'E') {
                  return null;
               }

               var1.q(1);
               var1.gO = var2;
               return var3;
            }
         } while (++var5 <= 1000);

         return null;
      }
   }

   private azb PV(ayw var1) {
      switch (var1.q()) {
         case 'I':
         case 'J':
            return this.Hk(var1);
         case 'L':
            return this.Gf(var1);
         case 'X':
            var1.q(1);
            azb var2 = this.KT(var1);
            if (!var1.q('E')) {
               return null;
            }

            return var2;
         default:
            return this.za(var1);
      }
   }

   private azb RF(ayw var1, char var2) {
      azb var3 = null;
      azb var4 = null;
      if (var1.q() == var2) {
         var1.q(1);
         return this.q(var1, azd.GY, null, null);
      } else {
         int var5 = 0;

         do {
            azb var6 = this.KT(var1);
            if (var6 == null) {
               return null;
            }

            if (var3 == null) {
               var3 = this.q(var1, azd.GY, var6, null);
               if (var3 == null) {
                  return null;
               }

               var4 = var3;
            } else {
               var4.Dw.lm().RF = this.q(var1, azd.GY, var6, null);
               if (var4.RF() == null) {
                  return null;
               }

               var4 = var4.RF();
            }

            if (var1.q() == var2) {
               var1.q(1);
               return var3;
            }
         } while (++var5 <= 1000);

         return null;
      }
   }

   static boolean q(azb var0) {
      String var1 = var0.Dw.RF().q.q;
      return var1.charAt(1) == 'c' && (var1.charAt(0) == 's' || var1.charAt(0) == 'd' || var1.charAt(0) == 'c' || var1.charAt(0) == 'r');
   }

   private azb oQ(ayw var1) {
      var1.q(2);
      char var3 = var1.q();
      azb var2;
      if (var1.lm != 0 && (Character.isDigit(var3) || Character.isLowerCase(var3) || var3 == 'C' || var3 == 'U' || var3 == 'L')) {
         var1.lm = -1;
         var2 = this.Dw(var1, false);
         if (var1.q() == 'E') {
            var1.q(1);
         }
      } else {
         var2 = this.za(var1);
      }

      azb var4 = this.q(var1, var2, null);
      if (var1.q() == 'I') {
         var4 = this.q(var1, azd.Uv, var4, this.Hk(var1));
      }

      return var4;
   }

   private azb xW(ayw var1) {
      char var2 = var1.q();
      if (var2 == 'L') {
         return this.Gf(var1);
      } else if (var2 == 'T') {
         return this.qa(var1);
      } else if (var2 == 's' && var1.RF() == 'r') {
         return this.oQ(var1);
      } else if (var2 == 's' && var1.RF() == 'p') {
         var1.q(2);
         return this.q(var1, azd.qR, this.xW(var1), null);
      } else if (var2 == 'f' && var1.RF() == 'p') {
         var1.q(2);
         int var14;
         if (var1.q() == 'T') {
            var1.q(1);
            var14 = 0;
         } else {
            var14 = this.io(var1);
            if (var14 == Integer.MAX_VALUE || var14 == -1) {
               return null;
            }

            var14++;
         }

         return this.RF(var1, var14);
      } else if (!Character.isDigit(var2) && (var2 != 'o' || var1.RF() != 'n')) {
         if ((var2 == 'i' || var2 == 't') && var1.RF() == 'l') {
            azb var12 = null;
            var1.q(2);
            if (var2 == 't') {
               var12 = this.za(var1);
            }

            return var1.q() != 0 && var1.RF() != 0 ? this.q(var1, azd.CY, var12, this.RF(var1, 'E')) : null;
         } else if (var2 == 'u') {
            var1.q(1);
            azb var11 = this.xK(var1);
            azb var15 = this.Me(var1);
            return this.q(var1, azd.nv, var11, var15);
         } else {
            azb var10 = this.oW(var1);
            if (var10 == null) {
               return null;
            } else {
               String var4 = null;
               if (var10.q == azd.WI) {
                  var4 = var10.Dw.RF().q.q;
                  var1.nf = var1.nf + (var10.Dw.RF().q.RF.length() - 2);
                  if (var4.equals("st")) {
                     return this.q(var1, azd.lF, var10, this.za(var1));
                  }
               }

               int var5;
               switch (var10.q) {
                  case WI:
                     var5 = var10.Dw.RF().q.xK;
                     break;
                  case Tq:
                     var5 = var10.Dw.xK().q;
                     break;
                  case Yp:
                     var5 = 1;
                     break;
                  default:
                     return null;
               }

               switch (var5) {
                  case 0:
                     return this.q(var1, azd.nY, var10, null);
                  case 1:
                     boolean var17 = false;
                     if (var4 != null && (var4.charAt(0) == 'p' || var4.charAt(0) == 'm') && var4.charAt(1) == var4.charAt(0)) {
                        var17 = !var1.q('_');
                     }

                     azb var19;
                     if (var10.q == azd.Yp && var1.q('_')) {
                        var19 = this.RF(var1, 'E');
                     } else if (var4 != null && var4.equals("sP")) {
                        var19 = this.Me(var1);
                     } else {
                        var19 = this.xW(var1);
                     }

                     if (var17) {
                        var19 = this.q(var1, azd.NX, var19, var19);
                     }

                     return this.q(var1, azd.lF, var10, var19);
                  case 2:
                     if (var4 == null) {
                        return null;
                     }

                     azb var16;
                     if (q(var10)) {
                        var16 = this.za(var1);
                     } else if (var4.charAt(0) == 'f') {
                        var16 = this.oW(var1);
                     } else if (var4.equals("di")) {
                        var16 = this.q(var1, null, null);
                     } else {
                        var16 = this.xW(var1);
                     }

                     azb var18;
                     if (var4.equals("cl")) {
                        var18 = this.RF(var1, 'E');
                     } else if (!var4.equals("dt") && !var4.equals("pt")) {
                        var18 = this.xW(var1);
                     } else {
                        var2 = var1.q();
                        if ((var2 != 'g' || var1.RF() != 's') && (var2 != 's' || var1.RF() != 'r')) {
                           var18 = this.q(var1, null, null);
                           if (var1.q() == 'I') {
                              var18 = this.q(var1, azd.Uv, var18, this.Hk(var1));
                           }
                        } else {
                           var18 = this.xW(var1);
                        }
                     }

                     return this.q(var1, azd.nq, var10, this.q(var1, azd.NX, var16, var18));
                  case 3:
                     azb var8 = null;
                     if (var4 == null) {
                        return null;
                     } else {
                        azb var6;
                        azb var7;
                        if (!var4.equals("qu") && !var4.equals("dX")) {
                           if (var4.charAt(0) == 'f') {
                              var6 = this.oW(var1);
                              var7 = this.xW(var1);
                              var8 = this.xW(var1);
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

                              var6 = this.RF(var1, '_');
                              var7 = this.za(var1);
                              if (var1.q() == 'E') {
                                 var1.q(1);
                              } else if (var1.q() == 'p' && var1.RF() == 'i') {
                                 var1.q(2);
                                 var8 = this.RF(var1, 'E');
                              } else {
                                 if (var1.q() != 'i' || var1.RF() != 'l') {
                                    return null;
                                 }

                                 var8 = this.xW(var1);
                              }
                           }
                        } else {
                           var6 = this.xW(var1);
                           var7 = this.xW(var1);
                           var8 = this.xW(var1);
                           if (var8 == null) {
                              return null;
                           }
                        }

                        return this.q(var1, azd.br, var10, this.q(var1, azd.tW, var6, this.q(var1, azd.ZA, var7, var8)));
                     }
                  default:
                     return null;
               }
            }
         }
      } else {
         if (var2 == 'o') {
            var1.q(2);
         }

         azb var3 = this.q(var1, null, null);
         if (var3 == null) {
            return null;
         } else {
            return var1.q() == 'I' ? this.q(var1, azd.Uv, var3, this.Hk(var1)) : var3;
         }
      }
   }

   private azb KT(ayw var1) {
      boolean var2 = var1.gP;
      var1.gP = true;
      azb var3 = this.xW(var1);
      var1.gP = var2;
      return var3;
   }

   private azb Gf(ayw var1) {
      if (!var1.q('L')) {
         return null;
      } else {
         azb var2;
         if (var1.q() != '_' && var1.q() != 'Z') {
            azb var3 = this.za(var1);
            if (var3 == null) {
               return null;
            }

            if (var3.q == azd.rL && var3.Dw.oW().q.xK != aza.eo.q) {
               var1.nf = var1.nf - var3.Dw.oW().q.q.length();
            }

            if (var3.q == azd.rL && var3.Dw.oW().q.q.equals(aza.q(33).q) && var1.q() == 'E') {
               var1.q(1);
               return var3;
            }

            azd var4 = azd.Ov;
            if (var1.q() == 'n') {
               var4 = azd.Lj;
               var1.q(1);
            }

            String var5 = var1.Dw();

            while (var1.q() != 'E') {
               if (var1.q() == 0) {
                  return null;
               }

               var1.q(1);
            }

            int var6 = var5.length() - var1.Dw().length();
            var2 = this.q(var1, var4, var3, this.q(var1, var5, var6));
         } else {
            var2 = this.q(var1, false);
         }

         return !var1.q('E') ? null : var2;
      }
   }

   private azb Ef(ayw var1) {
      if (!var1.q('Z')) {
         return null;
      } else {
         azb var2 = this.RF(var1, false);
         if (var2 == null) {
            return null;
         } else if (!var1.q('E')) {
            return null;
         } else {
            azb var3;
            if (var1.q() == 's') {
               var1.q(1);
               if (!this.cC(var1)) {
                  return null;
               }

               var3 = this.q(var1, "string literal", "string literal".length());
            } else {
               int var4 = -1;
               if (var1.q() == 'd') {
                  var1.q(1);
                  var4 = this.io(var1);
                  if (var4 < 0) {
                     return null;
                  }
               }

               var3 = this.xK(var1, false);
               if (var3 != null && var3.q != azd.kf && var3.q != azd.TQ && !this.cC(var1)) {
                  return null;
               }

               if (var4 >= 0) {
                  var3 = this.RF(var1, var4, var3);
               }
            }

            if (var2.q == azd.Dw && var2.RF().q == azd.YN) {
               azb var5 = var2.RF();
               var5.Dw.lm().q = null;
            }

            return this.q(var1, azd.xK, var2, var3);
         }
      }
   }

   private boolean cC(ayw var1) {
      int var3 = 1;
      if (var1.q() != '_') {
         return true;
      } else {
         var1.q(1);
         if (var1.q() == '_') {
            var3++;
            var1.q(1);
         }

         int var2 = this.Dw(var1);
         if (var2 < 0) {
            return false;
         } else {
            if (var3 > 1 && var2 >= 10) {
               if (var1.q() != '_') {
                  return false;
               }

               var1.q(1);
            }

            return true;
         }
      }
   }

   private azb q(ayw var1, azg var2) {
      if (var1.q() != 'T') {
         return null;
      } else {
         azb var3;
         azd var4;
         switch (var1.RF()) {
            case 'n':
               var1.q(2);
               var3 = this.za(var1);
               var4 = azd.os;
               if (var3 == null) {
                  var2.q = 1;
                  return null;
               }
               break;
            case 'p':
               var1.q(2);
               var3 = this.q(var1, var2);
               var4 = azd.fn;
               if (var3 == null) {
                  var2.q = 1;
                  return null;
               }
               break;
            case 't':
               var1.q(2);
               var3 = this.RF(var1, var2);
               var4 = azd.iu;
               if (var3 == null || !var1.q('E')) {
                  var2.q = 1;
                  return null;
               }
               break;
            case 'y':
               var1.q(2);
               var3 = null;
               var4 = azd.of;
               break;
            default:
               return null;
         }

         return this.q(var1, var4, var3, null);
      }
   }

   private azb RF(ayw var1, azg var2) {
      azb var3 = null;

      azb var4;
      while ((var4 = this.q(var1, var2)) != null) {
         if (var3 == null) {
            var3 = var4;
         } else {
            var3.Dw.lm().RF = var4;
            var3 = var4;
         }
      }

      azb var5 = var3;
      if (var3 != null) {
         var5 = this.q(var1, azd.vC, var3, null);
      }

      return var5;
   }

   private azb sH(ayw var1) {
      if (!var1.q('U')) {
         return null;
      } else if (!var1.q('l')) {
         return null;
      } else {
         azg var2 = new azg(0);
         azb var3 = this.RF(var1, var2);
         if (var2.q != 0) {
            return null;
         } else {
            azb var4 = this.zz(var1);
            if (var4 == null) {
               return null;
            } else {
               if (var3 != null) {
                  var3.Dw.lm().RF = var4;
                  var4 = var3;
               }

               if (!var1.q('E')) {
                  return null;
               } else {
                  int var5 = this.io(var1);
                  if (var5 < 0) {
                     return null;
                  } else {
                     azb var6 = this.q(var1);
                     if (var6 != null) {
                        var6.q = azd.kf;
                        var6.Dw = new azb.oL();
                        var6.Dw.zz().q = var4;
                        var6.Dw.zz().RF = var5;
                     }

                     return var6;
                  }
               }
            }
         }
      }
   }

   private azb CE(ayw var1) {
      if (!var1.q('U')) {
         return null;
      } else if (!var1.q('t')) {
         return null;
      } else {
         int var2 = this.io(var1);
         if (var2 < 0) {
            return null;
         } else {
            azb var3 = this.q(var1);
            if (var3 != null) {
               var3.q = azd.TQ;
               var3.Dw = new azb.vn();
               var3.Dw.gP().q = var2;
            }

            var1.q(var3);
            return var3;
         }
      }
   }

   private azb Dw(ayw var1, azb var2) {
      String var3 = var1.Dw();
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

      var1.q(var3.length() - var4.length());
      azb var5 = this.q(var1, var3, var3.length() - var4.length());
      return this.q(var1, azd.Wp, var2, var5);
   }

   private azb Uv(ayw var1, int var2) {
      if (!var1.q('S')) {
         return null;
      } else {
         char var3 = var1.xK();
         if (var3 != '_' && !Character.isDigit(var3) && !Character.isUpperCase(var3)) {
            boolean var9 = false;
            if (!var9 && var2 != 0) {
               char var10 = var1.q();
               if (var10 == 'C' || var10 == 'D') {
                  var9 = true;
               }
            }

            for (ayy var12 : ayy.Uv) {
               if (var12.q == var3) {
                  if (var12.Dw != null) {
                     var1.gO = this.q(var1, var12.Dw);
                  }

                  String var7;
                  if (var9) {
                     var7 = var12.xK;
                  } else {
                     var7 = var12.RF;
                  }

                  var1.nf = var1.nf + var7.length();
                  azb var8 = this.q(var1, var7);
                  if (var1.q() == 'B') {
                     var8 = this.RF(var1, var8);
                     var1.q(var8);
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
                  var3 = var1.xK();
                  if (++var5 > 1000) {
                     return null;
                  }
               } while (var3 != '_');

               var4 = var6 + 1;
            }

            return var4 >= var1.oW.size() ? null : (azb)var1.oW.get(var4);
         }
      }
   }
}
