package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.primitives.Characters;

class ayu {
   private static final int q = 1024;

   public void q(ayx var1, azb var2) {
      if (var2 != null && var2.RF <= 1 && var1.Uv <= 1024) {
         var2.RF++;
         var1.Uv++;
         int var3 = var1.gP.size();
         var1.gP.add(0, var2);
         int var4 = 0;
         if (var2.Uv) {
            var4 = var1.q.length();
         }

         this.RF(var1, var2);
         if (var2.Uv) {
            var2.oW = var1.q.substring(var4).toString();
         }

         while (var1.gP.size() > var3) {
            var1.gP.remove(0);
         }

         var2.RF--;
         var1.Uv--;
      } else {
         throw new RuntimeException("Printing error");
      }
   }

   private void RF(ayx var1, azb var2) {
      if (var2 == null) {
         throw new RuntimeException("printing error");
      } else if (var1.Dw) {
         throw new RuntimeException("Demangle error");
      } else {
         azb var3 = null;
         ayx.CU var4 = null;
         boolean var5 = false;
         boolean var6 = false;
         switch (var2.q) {
            case q:
               var1.q.append(var2.Dw.q().q);
               return;
            case YA:
               this.q(var1, var2.q());
               var1.q.append("[abi:");
               this.q(var1, var2.RF());
               var1.q.append(']');
               return;
            case eC:
               var1.q.append('[');

               while (true) {
                  this.q(var1, var2.q());
                  var2 = var2.RF();
                  if (var2 == null) {
                     var1.q.append(']');
                     return;
                  }

                  var1.q.append(", ");
               }
            case jh:
               this.q(var1, var2.q());
               var1.q.append('@');
               this.q(var1, var2.RF());
               return;
            case ND:
            case Qu:
               if (var2.q() != null) {
                  this.q(var1, var2.q());
               }

               int var42 = var2.q == azd.Qu ? 58 : (var2.q() != null ? 46 : 0);
               if (var42 != 0) {
                  var1.q.append((char)var42);
               }

               this.q(var1, var2.RF());
               return;
            case RF:
            case xK:
               this.q(var1, var2.q());
               var1.q.append("::");
               azb var41 = var2.RF();
               if (var41.q == azd.GM) {
                  var1.q.append("{default arg#");
                  var1.q.append(var41.Dw.zz().RF + 1);
                  var1.q.append("}::");
                  var41 = var41.Dw.zz().q;
               }

               this.q(var1, var41);
               return;
            case Dw:
               ayx.eo[] var64 = new ayx.eo[]{new ayx.eo(), new ayx.eo(), new ayx.eo(), new ayx.eo()};
               ayx.CU var74 = new ayx.CU();
               ayx.eo var40 = var1.xK;
               var1.xK = null;
               int var71 = 0;

               azb var56;
               for (var56 = var2.q(); var56 != null; var56 = var56.q()) {
                  if (var71 >= var64.length) {
                     throw new RuntimeException("Printing error");
                  }

                  var64[var71].q = var1.xK;
                  var1.xK = var64[var71];
                  var64[var71].RF = var56;
                  var64[var71].xK = 0;
                  var64[var71].Dw = var1.RF;
                  var71++;
                  if (!ays.q(var56.q)) {
                     break;
                  }
               }

               if (var56 == null) {
                  throw new RuntimeException("Printing error");
               }

               if (var56.q == azd.xK) {
                  var56 = var56.RF();
                  if (var56.q == azd.GM) {
                     var56 = var56.Dw.zz().q;
                  }

                  while (var56 != null && ays.q(var56.q)) {
                     if (var71 >= var64.length) {
                        throw new RuntimeException("Printing error");
                     }

                     var64[var71] = new ayx.eo(var64[var71 - 1]);
                     var64[var71].q = var64[var71 - 1];
                     var1.xK = var64[var71];
                     var64[var71 - 1].RF = var56;
                     var64[var71 - 1].xK = 0;
                     var64[var71 - 1].Dw = var1.RF;
                     var71++;
                     var56 = var56.q();
                  }

                  if (var56 == null) {
                     throw new RuntimeException("Printing error");
                  }
               }

               if (var56.q == azd.Uv) {
                  var74.q = var1.RF;
                  var1.RF = var74;
                  var74.RF = var56;
                  azb var76 = var56.RF();
                  if (var76.q == azd.mJ) {
                     var56.RF(var76.q());
                     var76.q(var2.RF());
                     var2.RF(var76);
                  }
               }

               this.q(var1, var2.RF());
               if (var56.q == azd.Uv) {
                  var1.RF = var74.q;
               }

               while (var71 > 0) {
                  if (var64[--var71].xK == 0) {
                     var1.q.append(' ');
                     this.lm(var1, var64[var71].RF);
                  }
               }

               var1.xK = var40;
               return;
            case Uv:
               azb var63 = var1.zz;
               var1.zz = var2;
               ayx.eo var39 = var1.xK;
               var1.xK = null;
               azb var55 = var2.q();
               this.q(var1, var55);
               if (var1.q.charAt(var1.q.length() - 1) == '<') {
                  var1.q.append(' ');
               }

               var1.q.append('<');
               this.q(var1, var2.RF());
               if (var1.q.charAt(var1.q.length() - 1) == '>') {
                  var1.q.append(' ');
               }

               var1.q.append('>');
               var1.xK = var39;
               var1.zz = var63;
               return;
            case oW:
               if (var1.oW > var2.Dw.gP().q + 1L) {
                  azb var37 = var1.RF.RF.q();

                  for (long var53 = var2.Dw.gP().q; var37 != null && var53 != 0L; var53--) {
                     var37 = var37.RF();
                  }

                  if (var37 != null && var37.q == azd.fn) {
                     var37 = var37.q();
                  }

                  if (var37 == null) {
                     var1.Dw = true;
                  } else {
                     this.q(var1, var37.q, var2.Dw.gP().q);
                  }
               } else if (var1.oW > 0) {
                  var1.q.append("auto:");
                  var1.q.append(var2.Dw.gP().q + 1L);
               } else {
                  azb var54;
                  try {
                     var54 = this.za(var1, var2);
                  } catch (azf var16) {
                     return;
                  }

                  if (var54 != null && var54.q == azd.Wx) {
                     var54 = this.q(var54, var1.gO);
                  }

                  ayx.CU var38 = var1.RF;
                  var1.RF = var38.q;
                  if (var54 != null) {
                     this.q(var1, var54);
                  }

                  var1.RF = var38;
               }

               return;
            case AB:
               var1.q.append("template parameter object for ");
               this.q(var1, var2.q());
               return;
            case nf:
               this.q(var1, var2.Dw.Dw().RF);
               return;
            case gP:
               var1.q.append('~');
               this.q(var1, var2.Dw.Uv().RF);
               return;
            case Jf:
               var1.q.append("initializer for module ");
               this.q(var1, var2.q());
               return;
            case za:
               var1.q.append("vtable for ");
               this.q(var1, var2.q());
               return;
            case lm:
               var1.q.append("VTT for ");
               this.q(var1, var2.q());
               return;
            case zz:
               var1.q.append("construction vtable for ");
               this.q(var1, var2.q());
               var1.q.append("-in-");
               this.q(var1, var2.RF());
               return;
            case JY:
               var1.q.append("typeinfo for ");
               this.q(var1, var2.q());
               return;
            case HF:
               var1.q.append("typeinfo name for ");
               this.q(var1, var2.q());
               return;
            case LK:
               var1.q.append("typeinfo fn for ");
               this.q(var1, var2.q());
               return;
            case io:
               var1.q.append("non-virtual thunk to ");
               this.q(var1, var2.q());
               return;
            case qa:
               var1.q.append("virtual thunk to ");
               this.q(var1, var2.q());
               return;
            case Hk:
               var1.q.append("covariant return thunk to ");
               this.q(var1, var2.q());
               return;
            case Me:
               var1.q.append("java Class for ");
               this.q(var1, var2.q());
               return;
            case PV:
               var1.q.append("guard variable for ");
               this.q(var1, var2.q());
               return;
            case oQ:
               var1.q.append("TLS init function for ");
               this.q(var1, var2.q());
               return;
            case xW:
               var1.q.append("TLS wrapper function for ");
               this.q(var1, var2.q());
               return;
            case KT:
               var1.q.append("reference temporary #");
               this.q(var1, var2.RF());
               var1.q.append(" for ");
               this.q(var1, var2.q());
               return;
            case Gf:
               var1.q.append("hidden alias for ");
               this.q(var1, var2.q());
               return;
            case Yw:
               var1.q.append("transaction clone for ");
               this.q(var1, var2.q());
               return;
            case IY:
               var1.q.append("non-transaction clone for ");
               this.q(var1, var2.q());
               return;
            case Ef:
               var1.q.append(var2.Dw.nf().q);
               return;
            case cC:
            case sH:
            case CE:
               for (ayx.eo var34 = var1.xK; var34 != null; var34 = var34.q) {
                  if (var34.xK == 0) {
                     if (var34.RF.q != azd.cC && var34.RF.q != azd.sH && var34.RF.q != azd.CE) {
                        break;
                     }

                     if (var34.RF.q == var2.q) {
                        this.q(var1, var2.q());
                        return;
                     }
                  }
               }

               var6 = true;
            case EB:
            case Xo:
               if (!var6) {
                  azb var35 = var2.q();
                  if (var1.oW == 0 && var35.q == azd.oW) {
                     ayx.nI var52 = this.gP(var1, var35);
                     azb var62 = null;
                     if (var52 == null) {
                        this.nf(var1, var35);
                        if (var1.Dw) {
                           return;
                        }
                     } else {
                        boolean var70 = false;
                        int var73 = 0;

                        for (azb var77 : var1.gP) {
                           if (var77 == var35 || var77 == var2 && var73 != 0) {
                              var70 = true;
                              break;
                           }

                           var73++;
                        }

                        if (!var70) {
                           var4 = var1.RF;
                           var1.RF = var52.RF;
                           var5 = true;
                        }
                     }

                     try {
                        var62 = this.za(var1, var35);
                     } catch (azf var15) {
                     }

                     if (var62 != null && var62.q == azd.Wx) {
                        var62 = this.q(var62, var1.gO);
                     }

                     if (var62 == null) {
                        if (var5) {
                           var1.RF = var4;
                        }

                        return;
                     }

                     var35 = var62;
                  }

                  if (var35.q == azd.EB || var35.q == var2.q) {
                     var2 = var35;
                  } else if (var35.q == azd.Xo) {
                     var3 = var35.q();
                  }
               }
            case TX:
            case Rr:
            case Bu:
            case IN:
            case wF:
            case If:
            case Dz:
            case mI:
            case jq:
            case ui:
            case fw:
            case PY:
            case cR:
               break;
            case rL:
               var1.q.append(var2.Dw.oW().q.q);
               return;
            case Bs:
               var1.q.append(var2.Dw.gO().q.q);
               if (var2.Dw.gO().xK) {
                  var1.q.append("(");
               }

               if (var2.Dw.gO().RF.RF != null) {
                  this.q(var1, var2.Dw.gO().RF.RF);
               } else {
                  var1.q.append(var2.Dw.gO().RF.q);
               }

               if (var2.Dw.gO().Dw != 0) {
                  var1.q.append(var2.Dw.gO().Dw);
               }

               if (var2.Dw.gO().xK) {
                  var1.q.append(")");
               }

               return;
            case eJ:
               this.q(var1, var2.q());
               if (var2.RF() != null) {
                  var1.q.append('(');
                  this.q(var1, var2.RF());
                  var1.q.append(')');
               }

               return;
            case YN:
               if (var2.q() != null) {
                  ayx.eo var33 = new ayx.eo();
                  var33.q = var1.xK;
                  var1.xK = var33;
                  var33.RF = var2;
                  var33.xK = 0;
                  var33.Dw = var1.RF;
                  this.q(var1, var2.q());
                  var1.xK = var33.q;
                  if (var33.xK != 0) {
                     return;
                  }

                  if (!var1.q.isEmpty()) {
                     var1.q.append(" ");
                  }
               }

               this.q(var1, var2, var1.xK);
               return;
            case Rv:
               ayx.eo[] var51 = new ayx.eo[]{new ayx.eo(), new ayx.eo(), new ayx.eo(), new ayx.eo()};
               ayx.eo var32 = var1.xK;
               var51[0].q = var32;
               var1.xK = var51[0];
               var51[0].RF = var2;
               var51[0].xK = 0;
               var51[0].Dw = var1.RF;
               int var61 = 1;

               for (ayx.eo var69 = var32;
                  var69 != null && var69.RF != null && (var69.RF.q == azd.cC || var69.RF.q == azd.sH || var69.RF.q == azd.CE);
                  var69 = var69.q
               ) {
                  if (var69.xK == 0) {
                     if (var61 >= var51.length) {
                        throw new RuntimeException();
                     }

                     var51[var61] = new ayx.eo(var69);
                     var51[var61].q = var1.xK;
                     var1.xK = var51[var61];
                     var69.xK = 1;
                     var61++;
                  }
               }

               this.q(var1, var2.RF());
               var1.xK = var32;
               if (var51[0].xK != 0) {
                  return;
               }

               while (var61 > 1) {
                  this.lm(var1, var51[--var61].RF);
               }

               this.RF(var1, var2, var1.xK);
               return;
            case zx:
            case Ri:
               ayx.eo var31 = new ayx.eo();
               var31.q = var1.xK;
               var1.xK = var31;
               var31.RF = var2;
               var31.xK = 0;
               var31.Dw = var1.RF;
               this.q(var1, var2.RF());
               if (var31.xK == 0) {
                  this.lm(var1, var2);
               }

               var1.xK = var31.q;
               return;
            case ZT:
               if (var2.Dw.JY().xK) {
                  var1.q.append("_Sat ");
               }

               if (var2.Dw.JY().q.Dw.oW().q != aza.q(8)) {
                  if (var2.Dw.JY().q.Dw.oW().q == aza.q(9)) {
                     var1.q.append(aza.q(9).RF);
                     var1.q.append(' ');
                  } else {
                     this.q(var1, var2.Dw.JY().q);
                     var1.q.append(' ');
                  }
               }

               if (var2.Dw.JY().RF) {
                  var1.q.append("_Accum");
               } else {
                  var1.q.append("_Fract");
               }

               return;
            case GY:
            case Wx:
               int var29 = var1.q.length();
               if (var2.q() != null) {
                  this.q(var1, var2.q());
               }

               if (var2.RF() != null) {
                  boolean var50 = false;
                  if (var29 != var1.q.length()) {
                     var1.q.append(", ");
                     var50 = true;
                  }

                  var29 = var1.q.length();
                  this.q(var1, var2.RF());
                  if (var50 && var1.q.length() == var29) {
                     var1.q.delete(var1.q.length() - 2, var1.q.length());
                  }
               }

               return;
            case CY:
               azb var28 = var2.q();
               azb var49 = var2.RF();

               try {
                  if (var28 != null && var28.q == azd.Rv) {
                     azb var60 = var28.RF();
                     if (var60 != null && var60.q == azd.rL && "char".equals(var60.Dw.oW().q.q)) {
                        StringBuilder var68 = new StringBuilder();
                        azb var72 = var49;

                        while (var72 != null) {
                           var72.q();
                           azb var12;
                           if (var72.q == azd.GY) {
                              var12 = var72.q();
                              var72 = var72.RF();
                           } else {
                              if (var72.q != azd.Ov) {
                                 break;
                              }

                              var12 = var72;
                              var72 = null;
                           }

                           try {
                              if (var12 != null && var12.q == azd.Ov) {
                                 azb var13 = var12.RF();
                                 char var14 = (char)Integer.parseInt(var13.Dw.q().q);
                                 if (!Characters.isAsciiCharOrCommonFormat(var14)) {
                                    var72 = var49;
                                    break;
                                 }

                                 var68.append(Formatter.escapeCharacter((char)var14));
                              }
                           } catch (NumberFormatException var17) {
                              var72 = var49;
                              break;
                           }
                        }

                        if (var72 == null) {
                           var1.q.append("\"").append((CharSequence)var68).append("\"");
                           return;
                        }
                     }
                  }
               } catch (Exception var18) {
               }

               if (var28 != null) {
                  this.q(var1, var28);
               }

               var1.q.append('{');
               this.q(var1, var49);
               var1.q.append('}');
               return;
            case WI:
               aze var27 = var2.Dw.RF().q;
               var1.q.append("operator");
               if (Character.isLowerCase(var27.RF.charAt(0))) {
                  var1.q.append(' ');
               }

               if (var27.RF.endsWith(" ")) {
                  var1.q.append(var27.RF.substring(0, var27.RF.length() - 1));
               } else {
                  var1.q.append(var27.RF);
               }

               return;
            case Tq:
               var1.q.append("operator ");
               this.q(var1, var2.Dw.xK().RF);
               return;
            case Yp:
               var1.q.append("operator ");
               this.q(var1, var2.q());
               return;
            case Gu:
               var1.q.append("operator ");
               this.HF(var1, var2);
               return;
            case nY:
               this.zz(var1, var2.q());
               return;
            case lF:
               azb var26 = var2.q();
               azb var47 = var2.RF();
               String var59 = null;
               if (var26.q == azd.WI) {
                  var59 = var26.Dw.RF().q.q;
                  if (var59.equals("ad") && var47.q == azd.Dw && var47.q().q == azd.RF && var47.RF().q == azd.YN) {
                     var47 = var47.q();
                  }

                  if (var47.q == azd.NX) {
                     var47 = var47.q();
                     this.gO(var1, var47);
                     this.zz(var1, var26);
                     return;
                  }
               }

               if (var59 != null && var59.equals("sZ")) {
                  azb var67 = this.oW(var1, var47);
                  int var11 = this.RF(var67);
                  var1.q.append(var11);
                  return;
               }

               if (var59 != null && var59.equals("sP")) {
                  int var66 = this.Uv(var1, var47);
                  var1.q.append(var66);
                  return;
               }

               if (var26.q != azd.Yp) {
                  this.zz(var1, var26);
               } else {
                  var1.q.append('(');
                  this.JY(var1, var26);
                  var1.q.append(')');
               }

               if (var59 != null && var59.equals("gs")) {
                  this.q(var1, var47);
               } else if (var59 == null
                  || !var59.equals("ti")
                     && !var59.equals("te")
                     && !var59.equals("st")
                     && !var59.equals("sz")
                     && !var59.equals("at")
                     && !var59.equals("az")
                     && !var59.equals("nx")) {
                  this.gO(var1, var47);
               } else {
                  var1.q.append('(');
                  this.q(var1, var47);
                  var1.q.append(')');
               }

               return;
            case nq:
               if (var2.RF().q != azd.NX) {
                  throw new RuntimeException("");
               }

               if (ays.q(var2.q())) {
                  this.zz(var1, var2.q());
                  var1.q.append('<');
                  this.q(var1, var2.RF().q());
                  var1.q.append(">(");
                  this.q(var1, var2.RF().RF());
                  var1.q.append(')');
                  return;
               }

               if (this.xK(var1, var2)) {
                  return;
               }

               if (this.Dw(var1, var2)) {
                  return;
               }

               boolean var25 = false;
               if (var2.q().q == azd.WI && var2.q().Dw.RF().q.RF.contains(">")) {
                  var1.q.append('(');
                  var25 = true;
               }

               if (var2.q().Dw.RF().q.q.equals("cl") && var2.RF().q().q == azd.Dw) {
                  azb var46 = var2.RF().q();
                  if (var46.RF().q != azd.YN) {
                     throw new RuntimeException();
                  }

                  this.gO(var1, var46.q());
               } else {
                  this.gO(var1, var2.RF().q());
               }

               if (var2.q().Dw.RF().q.q.equals("ix")) {
                  var1.q.append('[');
                  this.q(var1, var2.RF().RF());
                  var1.q.append(']');
               } else {
                  if (!var2.q().Dw.RF().q.q.equals("cl")) {
                     this.zz(var1, var2.q());
                  }

                  this.gO(var1, var2.RF().RF());
               }

               if (var25) {
                  var1.q.append(')');
               }

               return;
            case NX:
               throw new RuntimeException();
            case br:
               if (var2.RF().q == azd.tW && var2.RF().RF().q == azd.ZA) {
                  if (this.xK(var1, var2)) {
                     return;
                  }

                  if (this.Dw(var1, var2)) {
                     return;
                  }

                  azb var24 = var2.q();
                  azb var45 = var2.RF().q();
                  azb var58 = var2.RF().RF().q();
                  azb var65 = var2.RF().RF().RF();
                  if (var24.Dw.RF().q.q.equals("qu")) {
                     this.gO(var1, var45);
                     this.zz(var1, var24);
                     this.gO(var1, var58);
                     var1.q.append(" : ");
                     this.gO(var1, var65);
                  } else {
                     var1.q.append("new ");
                     if (var45.q() != null) {
                        this.gO(var1, var45);
                        var1.q.append(' ');
                     }

                     this.q(var1, var58);
                     if (var65 != null) {
                        this.gO(var1, var65);
                     }
                  }

                  return;
               }

               throw new RuntimeException();
            case tW:
            case ZA:
               throw new RuntimeException();
            case Ov:
            case Lj:
               aza.eo var23 = aza.eo.q;
               if (var2.q().q == azd.rL) {
                  var23 = var2.q().Dw.oW().q.xK;
                  switch (var23) {
                     case xK:
                     case Dw:
                     case Uv:
                     case oW:
                     case gO:
                     case RF:
                        if (var2.RF().q == azd.q) {
                           if (var2.q == azd.Lj) {
                              var1.q.append('-');
                           }

                           this.q(var1, var2.RF());
                           switch (var23) {
                              case xK:
                                 var1.q.append('u');
                                 break;
                              case Dw:
                                 var1.q.append('l');
                                 break;
                              case Uv:
                                 var1.q.append("ul");
                                 break;
                              case oW:
                                 var1.q.append("ll");
                                 break;
                              case gO:
                                 var1.q.append("ull");
                           }

                           return;
                        }
                        break;
                     case nf:
                        if (var2.RF().q == azd.q && var2.RF().Dw.q().q.length() == 1 && var2.q == azd.Ov) {
                           switch (var2.RF().Dw.q().q.charAt(0)) {
                              case '0':
                                 var1.q.append("false");
                                 return;
                              case '1':
                                 var1.q.append("true");
                                 return;
                           }
                        }
                  }
               }

               var1.q.append('(');
               this.q(var1, var2.q());
               var1.q.append(')');
               if (var2.q == azd.Lj) {
                  var1.q.append('-');
               }

               if (var23 == aza.eo.gP) {
                  var1.q.append('[');
               }

               this.q(var1, var2.RF());
               if (var23 == aza.eo.gP) {
                  var1.q.append(']');
               }

               return;
            case nv:
               this.q(var1, var2.q());
               var1.q.append('(');
               this.q(var1, var2.RF());
               var1.q.append(')');
               return;
            case fi:
               var1.q.append(var2.Dw.gP().q);
               return;
            case LL:
               var1.q.append("java resource ");
               this.q(var1, var2.q());
               return;
            case PQ:
               this.q(var1, var2.q());
               this.q(var1, var2.RF());
               return;
            case fQ:
               var1.q.append(var2.Dw.za().q);
               return;
            case bl:
               var1.q.append("decltype(");
               this.q(var1, var2.q());
               var1.q.append(')');
               return;
            case qR:
               azb var44 = null;
               if (var1.oW == 0) {
                  var44 = this.oW(var1, var2.q());
               }

               if (var44 == null) {
                  this.gO(var1, var2.q());
                  var1.q.append("...");
               } else {
                  int var22 = this.RF(var44);
                  var2 = var2.q();

                  for (int var57 = 0; var57 < var22; var57++) {
                     if (var57 != 0) {
                        var1.q.append(", ");
                     }

                     var1.gO = var57;
                     this.q(var1, var2);
                  }
               }

               return;
            case gO:
               long var21 = var2.Dw.gP().q;
               if (var21 == 0L) {
                  var1.q.append("this");
               } else {
                  var1.q.append("{parm#");
                  var1.q.append(var21);
                  var1.q.append('}');
               }

               return;
            case jb:
               var1.q.append("global constructors keyed to ");
               this.q(var1, var2.q());
               return;
            case pQ:
               var1.q.append("global destructors keyed to ");
               this.q(var1, var2.q());
               return;
            case kf:
               var1.q.append("{lambda");
               azb var20 = var2.Dw.zz().q;
               ayx.CU var43 = new ayx.CU();
               int var9 = var1.oW;
               var1.oW = 0;
               var43.RF = null;
               var43.q = var1.RF;
               var1.RF = var43;
               if (var20 != null && var20.q == azd.vC) {
                  var43.RF = var20;
                  var1.q.append('<');

                  for (azb var10 = var20.q(); var10 != null; var10 = var10.RF()) {
                     if (var1.oW++ != 0) {
                        var1.q.append(", ");
                     }

                     this.q(var1, var10);
                     var1.q.append(' ');
                     if (var10.q == azd.fn) {
                        var10 = var10.q();
                     }

                     this.q(var1, var10.q, var1.oW - 1);
                  }

                  var1.q.append('>');
                  var20 = var20.RF();
               }

               var1.oW++;
               var1.q.append('(');
               this.q(var1, var20);
               var1.oW = var9;
               var1.RF = var43.q;
               var1.q.append(")#");
               var1.q.append(var2.Dw.zz().RF + 1);
               var1.q.append('}');
               return;
            case TQ:
               var1.q.append("{unnamed type#");
               var1.q.append(var2.Dw.gP().q + 1L);
               var1.q.append('}');
               return;
            case Wp:
               this.q(var1, var2.q());
               var1.q.append(" [clone ");
               this.q(var1, var2.RF());
               var1.q.append(']');
               return;
            case cY:
               this.q(var1, var2.q());
               var1.q.append("[friend]");
               return;
            case vC:
               var1.q.append('<');
               int var7 = 0;

               for (azb var8 = var2.q(); var8 != null; var8 = var8.RF()) {
                  if (var7 != 0) {
                     var1.q.append(", ");
                  }

                  var7++;
                  this.q(var1, var8);
               }

               var1.q.append('>');
               return;
            case of:
               var1.q.append("typename");
               return;
            case os:
               this.q(var1, var2.q());
               return;
            case iu:
               var1.q.append("template");
               this.q(var1, var2.q());
               var1.q.append(" class");
               return;
            case fn:
               this.q(var1, var2.q());
               var1.q.append("...");
               return;
            case ZU:
               var1.q.append("struct ");
               this.q(var1, var2.q());
               return;
            case Sz:
               var1.q.append("union ");
               this.q(var1, var2.q());
               return;
            case fq:
               var1.q.append("enum ");
               this.q(var1, var2.q());
               return;
            case mJ:
               this.q(var1, var2.q());
               var1.q.append(" requires ");
               this.q(var1, var2.RF());
               return;
            default:
               throw new RuntimeException();
         }

         ayx.eo var36 = new ayx.eo();
         var36.q = var1.xK;
         var1.xK = var36;
         var36.RF = var2;
         var36.xK = 0;
         var36.Dw = var1.RF;
         if (var3 == null) {
            var3 = var2.q();
         }

         this.q(var1, var3);
         if (var36.xK == 0) {
            this.lm(var1, var2);
         }

         var1.xK = var36.q;
         if (var5) {
            var1.RF = var4;
         }
      }
   }

   private boolean xK(ayx var1, azb var2) {
      String var8 = var2.q().Dw.RF().q.q;
      if (!var8.startsWith("f")) {
         return false;
      } else {
         azb var3 = var2.RF();
         azb var4 = var3.q();
         azb var5 = var3.RF();
         azb var6 = null;
         if (var5.q == azd.ZA) {
            var6 = var5.RF();
            var5 = var5.q();
         }

         int var7 = var1.gO;
         var1.gO = -1;
         switch (var8.charAt(1)) {
            case 'L':
            case 'R':
               var1.q.append('(');
               this.gO(var1, var5);
               this.zz(var1, var4);
               var1.q.append("...");
               this.zz(var1, var4);
               this.gO(var1, var6);
               var1.q.append(')');
               break;
            case 'l':
               var1.q.append("(...");
               this.zz(var1, var4);
               this.gO(var1, var5);
               var1.q.append(')');
               break;
            case 'r':
               var1.q.append('(');
               this.gO(var1, var5);
               this.zz(var1, var4);
               var1.q.append("...)");
               break;
            default:
               throw new RuntimeException();
         }

         var1.gO = var7;
         return true;
      }
   }

   private boolean q(azb var1) {
      if (var1.q != azd.nq && var1.q != azd.br) {
         return false;
      } else {
         azb var2 = var1.q();
         String var3 = var2.Dw.RF().q.q;
         return var3.charAt(0) == 'd' && (var3.charAt(1) == 'i' || var3.charAt(1) == 'x' || var3.charAt(1) == 'X');
      }
   }

   private boolean Dw(ayx var1, azb var2) {
      if (!this.q(var2)) {
         return false;
      } else {
         String var3 = var2.q().Dw.RF().q.q;
         azb var4 = var2.RF();
         azb var5 = var4.q();
         azb var6 = var4.RF();
         if (var3.charAt(1) == 'i') {
            var1.q.append('.');
         } else {
            var1.q.append('[');
         }

         this.q(var1, var5);
         if (var3.charAt(1) == 'X') {
            var1.q.append(" ... ");
            this.q(var1, var6.q());
            var6 = var6.RF();
         }

         if (var3.charAt(1) != 'i') {
            var1.q.append(']');
         }

         if (this.q(var6)) {
            this.q(var1, var6);
         } else {
            var1.q.append('=');
            this.gO(var1, var6);
         }

         return true;
      }
   }

   private void q(ayx var1, azd var2, long var3) {
      var1.q.append(switch (var2) {
         case of -> "$T";
         case os -> "$N";
         case iu -> "$TT";
         default -> {
            var1.Dw = true;
            yield "";
         }
      });
      var1.q.append(var3);
   }

   private int Uv(ayx var1, azb var2) {
      int var3 = 0;

      while (var2 != null && var2.q == azd.Wx) {
         azb var4 = var2.q();
         if (var4 == null) {
            break;
         }

         if (var4.q == azd.qR) {
            azb var5 = this.oW(var1, var2.q());
            var3 += this.RF(var5);
         } else {
            var3++;
         }

         var2 = var2.RF();
      }

      return var3;
   }

   private int RF(azb var1) {
      int var2 = 0;

      while (var1 != null && var1.q == azd.Wx && var1.q() != null) {
         var2++;
         var1 = var1.RF();
      }

      return var2;
   }

   private azb oW(ayx var1, azb var2) {
      if (var2 == null) {
         return null;
      } else {
         switch (var2.q) {
            case q:
            case YA:
            case Ef:
            case rL:
            case Bs:
            case ZT:
            case WI:
            case fi:
            case fQ:
            case gO:
            case kf:
            case TQ:
            case GM:
               return null;
            case oW:
               azb var5;
               try {
                  var5 = this.za(var1, var2);
               } catch (azf var4) {
                  return null;
               }

               if (var5 != null && var5.q == azd.Wx) {
                  return var5;
               }

               return null;
            case nf:
               return this.oW(var1, var2.Dw.Dw().RF);
            case gP:
               return this.oW(var1, var2.Dw.Uv().RF);
            case Tq:
               return this.oW(var1, var2.Dw.xK().RF);
            case qR:
               return null;
            default:
               azb var3 = this.oW(var1, var2.q());
               return var3 != null ? var3 : this.oW(var1, var2.RF());
         }
      }
   }

   private void gO(ayx var1, azb var2) {
      boolean var3 = false;
      azd var4 = var2.q;
      if (var2.q == azd.oW) {
         try {
            azb var5 = this.za(var1, var2);
            if (var5 != null) {
               var4 = var5.q;
            }
         } catch (azf var6) {
         }
      }

      if (var4 == azd.q || var4 == azd.RF || var4 == azd.CY || var4 == azd.gO || var4 == azd.Ov || var4 == azd.Lj) {
         var3 = true;
      }

      if (!var3) {
         var1.q.append('(');
      }

      this.q(var1, var2);
      if (!var3) {
         var1.q.append(')');
      }
   }

   private void nf(ayx var1, azb var2) {
      ayx.nI var3 = new ayx.nI();
      var3.q = var2;
      ayx.CU var4 = var1.RF;
      if (var4 != null) {
         var3.RF = new ayx.CU();
         var3.RF.RF = var4.RF;
         var4 = var4.q;

         for (ayx.CU var5 = var3.RF; var4 != null; var4 = var4.q) {
            ayx.CU var6 = new ayx.CU();
            var6.RF = var4.RF;
            var5.q = var6;
            var5 = var6;
         }

         var1.za.add(var3);
      }
   }

   private ayx.nI gP(ayx var1, azb var2) {
      for (ayx.nI var4 : var1.za) {
         if (var4.q == var2) {
            return var4;
         }
      }

      return null;
   }

   private azb za(ayx var1, azb var2) throws azf {
      if (var1.RF != null && var1.RF.RF != null) {
         return this.q(var1.RF.RF.RF(), (int)var2.Dw.gP().q);
      } else {
         throw new azf();
      }
   }

   private azb q(azb var1, int var2) {
      if (var2 < 0) {
         return var1;
      } else {
         azb var3;
         for (var3 = var1; var3 != null; var3 = var3.RF()) {
            if (var3.q != azd.Wx) {
               return null;
            }

            if (var2 <= 0) {
               break;
            }

            var2--;
         }

         return var2 == 0 && var3 != null ? var3.q() : null;
      }
   }

   private void lm(ayx var1, azb var2) {
      switch (var2.q) {
         case Dw:
            this.q(var1, var2.q());
            return;
         case Uv:
         case oW:
         case AB:
         case nf:
         case gP:
         case Jf:
         case za:
         case lm:
         case zz:
         case JY:
         case HF:
         case LK:
         case io:
         case qa:
         case Hk:
         case Me:
         case PV:
         case oQ:
         case xW:
         case KT:
         case Gf:
         case Yw:
         case IY:
         case Ef:
         case rL:
         case Bs:
         case eJ:
         case YN:
         case Rv:
         default:
            this.q(var1, var2);
            return;
         case cC:
         case wF:
            var1.q.append(" restrict");
            return;
         case sH:
         case If:
            var1.q.append(" volatile");
            return;
         case CE:
         case Dz:
            var1.q.append(" const");
            return;
         case TX:
            var1.q.append(' ');
            this.q(var1, var2.RF());
            return;
         case Rr:
            var1.q.append('*');
            return;
         case Bu:
            var1.q.append(" _Complex");
            return;
         case IN:
            var1.q.append(" _Imaginary");
            return;
         case mI:
            var1.q.append(' ');
         case EB:
            var1.q.append('&');
            return;
         case jq:
            var1.q.append(' ');
         case Xo:
            var1.q.append("&&");
            return;
         case ui:
            return;
         case fw:
            var1.q.append(" transaction_safe");
            return;
         case PY:
            var1.q.append(" noexcept");
            if (var2.RF() != null) {
               var1.q.append('(');
               this.q(var1, var2.RF());
               var1.q.append(')');
            }

            return;
         case cR:
            var1.q.append(" throw");
            if (var2.RF() != null) {
               var1.q.append('(');
               this.q(var1, var2.RF());
               var1.q.append(')');
            }

            return;
         case zx:
            if (var1.q.charAt(var1.q.length() - 1) != '(') {
               var1.q.append(' ');
            }

            this.q(var1, var2.q());
            var1.q.append("::*");
            return;
         case Ri:
            var1.q.append(" __vector(");
            this.q(var1, var2.q());
            var1.q.append(')');
      }
   }

   private void q(ayx var1, azb var2, ayx.eo var3) {
      boolean var4 = false;
      boolean var5 = false;
      boolean var6 = false;

      for (ayx.eo var7 = var3; var7 != null && var7.RF != null && var7.xK == 0; var7 = var7.q) {
         switch (var7.RF.q) {
            case cC:
            case sH:
            case CE:
            case TX:
            case Bu:
            case IN:
            case zx:
               var5 = true;
               var4 = true;
               break;
            case EB:
            case Xo:
            case Rr:
               var4 = true;
            case wF:
            case If:
            case Dz:
            case mI:
            case jq:
            case fw:
            case PY:
            case cR:
            case rL:
            case Bs:
            case eJ:
            case YN:
            case Rv:
            default:
               break;
            case ui:
               var6 = true;
         }

         if (var4) {
            break;
         }
      }

      if (var4) {
         if (!var5 && var1.q.charAt(var1.q.length() - 1) != '(' && var1.q.charAt(var1.q.length() - 1) != '*') {
            var5 = true;
         }

         if (var5 && var1.q.charAt(var1.q.length() - 1) != ' ') {
            var1.q.append(' ');
         }

         var1.q.append('(');
      }

      ayx.eo var8 = var1.xK;
      var1.xK = null;
      this.q(var1, var3, false);
      if (var4) {
         var1.q.append(')');
      }

      var1.q.append('(');
      if (var6) {
         var1.q.append("this ");
      }

      if (var2.RF() != null) {
         this.q(var1, var2.RF());
      }

      var1.q.append(')');
      this.q(var1, var3, true);
      var1.xK = var8;
   }

   private void q(ayx var1, ayx.eo var2, boolean var3) {
      if (var2 != null && var2.RF != null && !var1.Dw) {
         if (var2.xK == 0 && (var3 || !ays.q(var2.RF.q))) {
            var2.xK = 1;
            ayx.CU var4 = var1.RF;
            var1.RF = var2.Dw;
            if (var2.RF.q == azd.YN) {
               this.q(var1, var2.RF, var2.q);
               var1.RF = var4;
            } else if (var2.RF.q == azd.Rv) {
               this.RF(var1, var2.RF, var2.q);
               var1.RF = var4;
            } else if (var2.RF.q == azd.xK) {
               ayx.eo var5 = var1.xK;
               var1.xK = null;
               this.q(var1, var2.RF.q());
               var1.xK = var5;
               var1.q.append("::");
               azb var6 = var2.RF.RF();
               azb var7 = var6;
               int var8 = 0;
               if (var6.Uv) {
                  var8 = var1.q.length();
               }

               if (var6.q == azd.GM) {
                  var1.q.append("{default arg#");
                  var1.q.append(var6.Dw.zz().RF + 1);
                  var1.q.append("}::");
                  var7 = var6.Dw.zz().q;
               }

               while (ays.q(var7.q)) {
                  var7 = var7.q();
               }

               this.q(var1, var7);
               if (var6.Uv && var6.q == azd.GM) {
                  var6.oW = var1.q.substring(var8).toString();
               }

               var1.RF = var4;
            } else if (var2.RF.q != azd.cR && var2.RF.q != azd.PY) {
               this.lm(var1, var2.RF);
               var1.RF = var4;
               this.q(var1, var2.q, var3);
            } else {
               this.q(var1, var2.q, var3);
               this.lm(var1, var2.RF);
               var1.RF = var4;
            }
         } else {
            this.q(var1, var2.q, var3);
         }
      }
   }

   private void RF(ayx var1, azb var2, ayx.eo var3) {
      boolean var4 = true;
      if (var3 != null) {
         boolean var5 = false;

         for (ayx.eo var6 = var3; var6 != null && var6.RF != null; var6 = var6.q) {
            if (var6.xK == 0) {
               if (var6.RF.q == azd.Rv) {
                  var4 = false;
               } else {
                  var5 = true;
                  var4 = true;
               }
               break;
            }
         }

         if (var5) {
            var1.q.append(" (");
         }

         this.q(var1, var3, false);
         if (var5) {
            var1.q.append(')');
         }
      }

      if (var4) {
         var1.q.append(' ');
      }

      var1.q.append('[');
      if (var2.q() != null) {
         this.q(var1, var2.q());
      }

      var1.q.append(']');
   }

   private void zz(ayx var1, azb var2) {
      if (var2.q == azd.WI) {
         var1.q.append(var2.Dw.RF().q.RF);
      } else {
         this.q(var1, var2);
      }
   }

   private void JY(ayx var1, azb var2) {
      this.q(var1, var2.q());
   }

   private void HF(ayx var1, azb var2) {
      ayx.CU var3 = new ayx.CU();
      if (var1.zz != null) {
         var3.q = var1.RF;
         var1.RF = var3;
         var3.RF = var1.zz;
      }

      if (var2.q().q != azd.Uv) {
         this.q(var1, var2.q());
         if (var1.zz != null) {
            var1.RF = var3.q;
         }
      } else {
         this.q(var1, var2.q().q());
         if (var1.zz != null) {
            var1.RF = var3.q;
         }

         if (var1.q.charAt(var1.q.length() - 1) == '<') {
            var1.q.append(' ');
         }

         var1.q.append('<');
         int var4 = var1.q.length();
         this.q(var1, var2.q().RF());
         if (var1.q.charAt(var1.q.length() - 1) == '>') {
            var1.q.append(' ');
         }

         if (var1.q.length() - var4 == 0) {
            var1.q.deleteCharAt(var4 - 1);
         } else if (var1.q.length() - var4 == 1 && var1.q.charAt(var4) == '*') {
            var1.q.delete(var4 - 1, var4 + 1);
         } else if (var1.q.length() - var4 == 6 && "(*)...".equals(var1.q.substring(var4, var4 + 6))) {
            var1.q.delete(var4 - 1, var4 + 6);
         } else {
            var1.q.append('>');
         }
      }
   }
}
