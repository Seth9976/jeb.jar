package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.primitives.Characters;

class avx {
   public void pC(awa var1, awe var2) {
      if (var2 != null && var2.A <= 1 && var1.UT <= 1024) {
         var2.A++;
         var1.UT++;
         int var3 = var1.ld.size();
         var1.ld.add(0, var2);
         int var4 = 0;
         if (var2.UT) {
            var4 = var1.pC.length();
         }

         this.A(var1, var2);
         if (var2.UT) {
            var2.E = var1.pC.substring(var4).toString();
         }

         while (var1.ld.size() > var3) {
            var1.ld.remove(0);
         }

         var2.A--;
         var1.UT--;
      } else {
         throw new RuntimeException("Printing error");
      }
   }

   private void A(awa var1, awe var2) {
      if (var2 == null) {
         throw new RuntimeException("printing error");
      } else if (var1.wS) {
         throw new RuntimeException("Demangle error");
      } else {
         awe var3 = null;
         awa.Sv var4 = null;
         boolean var5 = false;
         boolean var6 = false;
         switch (var2.pC) {
            case pC:
               var1.pC.append(var2.wS.pC().pC);
               return;
            case tH:
               this.pC(var1, var2.pC());
               var1.pC.append("[abi:");
               this.pC(var1, var2.A());
               var1.pC.append(']');
               return;
            case KK:
               var1.pC.append('[');

               while (true) {
                  this.pC(var1, var2.pC());
                  var2 = var2.A();
                  if (var2 == null) {
                     var1.pC.append(']');
                     return;
                  }

                  var1.pC.append(", ");
               }
            case LL:
               this.pC(var1, var2.pC());
               var1.pC.append('@');
               this.pC(var1, var2.A());
               return;
            case oB:
            case Rq:
               if (var2.pC() != null) {
                  this.pC(var1, var2.pC());
               }

               int var42 = var2.pC == awg.Rq ? 58 : (var2.pC() != null ? 46 : 0);
               if (var42 != 0) {
                  var1.pC.append((char)var42);
               }

               this.pC(var1, var2.A());
               return;
            case A:
            case kS:
               this.pC(var1, var2.pC());
               var1.pC.append("::");
               awe var41 = var2.A();
               if (var41.pC == awg.pt) {
                  var1.pC.append("{default arg#");
                  var1.pC.append(var41.wS.fI().A + 1);
                  var1.pC.append("}::");
                  var41 = var41.wS.fI().pC;
               }

               this.pC(var1, var41);
               return;
            case wS:
               awa.Av[] var64 = new awa.Av[]{new awa.Av(), new awa.Av(), new awa.Av(), new awa.Av()};
               awa.Sv var74 = new awa.Sv();
               awa.Av var40 = var1.kS;
               var1.kS = null;
               int var71 = 0;

               awe var56;
               for (var56 = var2.pC(); var56 != null; var56 = var56.pC()) {
                  if (var71 >= var64.length) {
                     throw new RuntimeException("Printing error");
                  }

                  var64[var71].pC = var1.kS;
                  var1.kS = var64[var71];
                  var64[var71].A = var56;
                  var64[var71].kS = 0;
                  var64[var71].wS = var1.A;
                  var71++;
                  if (!avv.pC(var56.pC)) {
                     break;
                  }
               }

               if (var56 == null) {
                  throw new RuntimeException("Printing error");
               }

               if (var56.pC == awg.kS) {
                  var56 = var56.A();
                  if (var56.pC == awg.pt) {
                     var56 = var56.wS.fI().pC;
                  }

                  while (var56 != null && avv.pC(var56.pC)) {
                     if (var71 >= var64.length) {
                        throw new RuntimeException("Printing error");
                     }

                     var64[var71] = new awa.Av(var64[var71 - 1]);
                     var64[var71].pC = var64[var71 - 1];
                     var1.kS = var64[var71];
                     var64[var71 - 1].A = var56;
                     var64[var71 - 1].kS = 0;
                     var64[var71 - 1].wS = var1.A;
                     var71++;
                     var56 = var56.pC();
                  }

                  if (var56 == null) {
                     throw new RuntimeException("Printing error");
                  }
               }

               if (var56.pC == awg.UT) {
                  var74.pC = var1.A;
                  var1.A = var74;
                  var74.A = var56;
                  awe var76 = var56.A();
                  if (var76.pC == awg.ii) {
                     var56.A(var76.pC());
                     var76.pC(var2.A());
                     var2.A(var76);
                  }
               }

               this.pC(var1, var2.A());
               if (var56.pC == awg.UT) {
                  var1.A = var74.pC;
               }

               while (var71 > 0) {
                  if (var64[--var71].kS == 0) {
                     var1.pC.append(' ');
                     this.oT(var1, var64[var71].A);
                  }
               }

               var1.kS = var40;
               return;
            case UT:
               awe var63 = var1.fI;
               var1.fI = var2;
               awa.Av var39 = var1.kS;
               var1.kS = null;
               awe var55 = var2.pC();
               this.pC(var1, var55);
               if (var1.pC.charAt(var1.pC.length() - 1) == '<') {
                  var1.pC.append(' ');
               }

               var1.pC.append('<');
               this.pC(var1, var2.A());
               if (var1.pC.charAt(var1.pC.length() - 1) == '>') {
                  var1.pC.append(' ');
               }

               var1.pC.append('>');
               var1.kS = var39;
               var1.fI = var63;
               return;
            case E:
               if (var1.E > var2.wS.ld().pC + 1L) {
                  awe var37 = var1.A.A.pC();

                  for (long var53 = var2.wS.ld().pC; var37 != null && var53 != 0L; var53--) {
                     var37 = var37.A();
                  }

                  if (var37 != null && var37.pC == awg.eE) {
                     var37 = var37.pC();
                  }

                  if (var37 == null) {
                     var1.wS = true;
                  } else {
                     this.pC(var1, var37.pC, var2.wS.ld().pC);
                  }
               } else if (var1.E > 0) {
                  var1.pC.append("auto:");
                  var1.pC.append(var2.wS.ld().pC + 1L);
               } else {
                  awe var54;
                  try {
                     var54 = this.gp(var1, var2);
                  } catch (awi var16) {
                     return;
                  }

                  if (var54 != null && var54.pC == awg.RW) {
                     var54 = this.pC(var54, var1.sY);
                  }

                  awa.Sv var38 = var1.A;
                  var1.A = var38.pC;
                  if (var54 != null) {
                     this.pC(var1, var54);
                  }

                  var1.A = var38;
               }

               return;
            case e:
               var1.pC.append("template parameter object for ");
               this.pC(var1, var2.pC());
               return;
            case ys:
               this.pC(var1, var2.wS.wS().A);
               return;
            case ld:
               var1.pC.append('~');
               this.pC(var1, var2.wS.UT().A);
               return;
            case rC:
               var1.pC.append("initializer for module ");
               this.pC(var1, var2.pC());
               return;
            case gp:
               var1.pC.append("vtable for ");
               this.pC(var1, var2.pC());
               return;
            case oT:
               var1.pC.append("VTT for ");
               this.pC(var1, var2.pC());
               return;
            case fI:
               var1.pC.append("construction vtable for ");
               this.pC(var1, var2.pC());
               var1.pC.append("-in-");
               this.pC(var1, var2.A());
               return;
            case WR:
               var1.pC.append("typeinfo for ");
               this.pC(var1, var2.pC());
               return;
            case NS:
               var1.pC.append("typeinfo name for ");
               this.pC(var1, var2.pC());
               return;
            case vP:
               var1.pC.append("typeinfo fn for ");
               this.pC(var1, var2.pC());
               return;
            case xC:
               var1.pC.append("non-virtual thunk to ");
               this.pC(var1, var2.pC());
               return;
            case ED:
               var1.pC.append("virtual thunk to ");
               this.pC(var1, var2.pC());
               return;
            case Sc:
               var1.pC.append("covariant return thunk to ");
               this.pC(var1, var2.pC());
               return;
            case ah:
               var1.pC.append("java Class for ");
               this.pC(var1, var2.pC());
               return;
            case eP:
               var1.pC.append("guard variable for ");
               this.pC(var1, var2.pC());
               return;
            case UO:
               var1.pC.append("TLS init function for ");
               this.pC(var1, var2.pC());
               return;
            case Ab:
               var1.pC.append("TLS wrapper function for ");
               this.pC(var1, var2.pC());
               return;
            case rl:
               var1.pC.append("reference temporary #");
               this.pC(var1, var2.A());
               var1.pC.append(" for ");
               this.pC(var1, var2.pC());
               return;
            case z:
               var1.pC.append("hidden alias for ");
               this.pC(var1, var2.pC());
               return;
            case Um:
               var1.pC.append("transaction clone for ");
               this.pC(var1, var2.pC());
               return;
            case Ta:
               var1.pC.append("non-transaction clone for ");
               this.pC(var1, var2.pC());
               return;
            case Ek:
               var1.pC.append(var2.wS.ys().pC);
               return;
            case hK:
            case Er:
            case FE:
               for (awa.Av var34 = var1.kS; var34 != null; var34 = var34.pC) {
                  if (var34.kS == 0) {
                     if (var34.A.pC != awg.hK && var34.A.pC != awg.Er && var34.A.pC != awg.FE) {
                        break;
                     }

                     if (var34.A.pC == var2.pC) {
                        this.pC(var1, var2.pC());
                        return;
                     }
                  }
               }

               var6 = true;
            case UW:
            case PR:
               if (!var6) {
                  awe var35 = var2.pC();
                  if (var1.E == 0 && var35.pC == awg.E) {
                     awa.K var52 = this.ld(var1, var35);
                     awe var62 = null;
                     if (var52 == null) {
                        this.ys(var1, var35);
                        if (var1.wS) {
                           return;
                        }
                     } else {
                        boolean var70 = false;
                        int var73 = 0;

                        for (awe var77 : var1.ld) {
                           if (var77 == var35 || var77 == var2 && var73 != 0) {
                              var70 = true;
                              break;
                           }

                           var73++;
                        }

                        if (!var70) {
                           var4 = var1.A;
                           var1.A = var52.A;
                           var5 = true;
                        }
                     }

                     try {
                        var62 = this.gp(var1, var35);
                     } catch (awi var15) {
                     }

                     if (var62 != null && var62.pC == awg.RW) {
                        var62 = this.pC(var62, var1.sY);
                     }

                     if (var62 == null) {
                        if (var5) {
                           var1.A = var4;
                        }

                        return;
                     }

                     var35 = var62;
                  }

                  if (var35.pC == awg.UW || var35.pC == var2.pC) {
                     var2 = var35;
                  } else if (var35.pC == awg.PR) {
                     var3 = var35.pC();
                  }
               }
            case Cu:
            case hZ:
            case cX:
            case DQ:
            case Aj:
            case EX:
            case LM:
            case mv:
            case sO:
            case os:
            case Gm:
            case AU:
            case jS:
               break;
            case ZN:
               var1.pC.append(var2.wS.E().pC.pC);
               return;
            case Gu:
               var1.pC.append(var2.wS.sY().pC.pC);
               if (var2.wS.sY().kS) {
                  var1.pC.append("(");
               }

               if (var2.wS.sY().A.A != null) {
                  this.pC(var1, var2.wS.sY().A.A);
               } else {
                  var1.pC.append(var2.wS.sY().A.pC);
               }

               if (var2.wS.sY().wS != 0) {
                  var1.pC.append(var2.wS.sY().wS);
               }

               if (var2.wS.sY().kS) {
                  var1.pC.append(")");
               }

               return;
            case OB:
               this.pC(var1, var2.pC());
               if (var2.A() != null) {
                  var1.pC.append('(');
                  this.pC(var1, var2.A());
                  var1.pC.append(')');
               }

               return;
            case pF:
               if (var2.pC() != null) {
                  awa.Av var33 = new awa.Av();
                  var33.pC = var1.kS;
                  var1.kS = var33;
                  var33.A = var2;
                  var33.kS = 0;
                  var33.wS = var1.A;
                  this.pC(var1, var2.pC());
                  var1.kS = var33.pC;
                  if (var33.kS != 0) {
                     return;
                  }

                  if (!var1.pC.isEmpty()) {
                     var1.pC.append(" ");
                  }
               }

               this.pC(var1, var2, var1.kS);
               return;
            case Bc:
               awa.Av[] var51 = new awa.Av[]{new awa.Av(), new awa.Av(), new awa.Av(), new awa.Av()};
               awa.Av var32 = var1.kS;
               var51[0].pC = var32;
               var1.kS = var51[0];
               var51[0].A = var2;
               var51[0].kS = 0;
               var51[0].wS = var1.A;
               int var61 = 1;

               for (awa.Av var69 = var32;
                  var69 != null && var69.A != null && (var69.A.pC == awg.hK || var69.A.pC == awg.Er || var69.A.pC == awg.FE);
                  var69 = var69.pC
               ) {
                  if (var69.kS == 0) {
                     if (var61 >= var51.length) {
                        throw new RuntimeException();
                     }

                     var51[var61] = new awa.Av(var69);
                     var51[var61].pC = var1.kS;
                     var1.kS = var51[var61];
                     var69.kS = 1;
                     var61++;
                  }
               }

               this.pC(var1, var2.A());
               var1.kS = var32;
               if (var51[0].kS != 0) {
                  return;
               }

               while (var61 > 1) {
                  this.oT(var1, var51[--var61].A);
               }

               this.A(var1, var2, var1.kS);
               return;
            case OI:
            case Pe:
               awa.Av var31 = new awa.Av();
               var31.pC = var1.kS;
               var1.kS = var31;
               var31.A = var2;
               var31.kS = 0;
               var31.wS = var1.A;
               this.pC(var1, var2.A());
               if (var31.kS == 0) {
                  this.oT(var1, var2);
               }

               var1.kS = var31.pC;
               return;
            case Bf:
               if (var2.wS.WR().kS) {
                  var1.pC.append("_Sat ");
               }

               if (var2.wS.WR().pC.wS.E().pC != awd.pC(8)) {
                  if (var2.wS.WR().pC.wS.E().pC == awd.pC(9)) {
                     var1.pC.append(awd.pC(9).A);
                     var1.pC.append(' ');
                  } else {
                     this.pC(var1, var2.wS.WR().pC);
                     var1.pC.append(' ');
                  }
               }

               if (var2.wS.WR().A) {
                  var1.pC.append("_Accum");
               } else {
                  var1.pC.append("_Fract");
               }

               return;
            case ck:
            case RW:
               int var29 = var1.pC.length();
               if (var2.pC() != null) {
                  this.pC(var1, var2.pC());
               }

               if (var2.A() != null) {
                  boolean var50 = false;
                  if (var29 != var1.pC.length()) {
                     var1.pC.append(", ");
                     var50 = true;
                  }

                  var29 = var1.pC.length();
                  this.pC(var1, var2.A());
                  if (var50 && var1.pC.length() == var29) {
                     var1.pC.delete(var1.pC.length() - 2, var1.pC.length());
                  }
               }

               return;
            case xM:
               awe var28 = var2.pC();
               awe var49 = var2.A();

               try {
                  if (var28 != null && var28.pC == awg.Bc) {
                     awe var60 = var28.A();
                     if (var60 != null && var60.pC == awg.ZN && "char".equals(var60.wS.E().pC.pC)) {
                        StringBuilder var68 = new StringBuilder();
                        awe var72 = var49;

                        while (var72 != null) {
                           var72.pC();
                           awe var12;
                           if (var72.pC == awg.ck) {
                              var12 = var72.pC();
                              var72 = var72.A();
                           } else {
                              if (var72.pC != awg.Xs) {
                                 break;
                              }

                              var12 = var72;
                              var72 = null;
                           }

                           try {
                              if (var12 != null && var12.pC == awg.Xs) {
                                 awe var13 = var12.A();
                                 char var14 = (char)Integer.parseInt(var13.wS.pC().pC);
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
                           var1.pC.append("\"").append((CharSequence)var68).append("\"");
                           return;
                        }
                     }
                  }
               } catch (Exception var18) {
               }

               if (var28 != null) {
                  this.pC(var1, var28);
               }

               var1.pC.append('{');
               this.pC(var1, var49);
               var1.pC.append('}');
               return;
            case kU:
               awh var27 = var2.wS.A().pC;
               var1.pC.append("operator");
               if (Character.isLowerCase(var27.A.charAt(0))) {
                  var1.pC.append(' ');
               }

               if (var27.A.endsWith(" ")) {
                  var1.pC.append(var27.A.substring(0, var27.A.length() - 1));
               } else {
                  var1.pC.append(var27.A);
               }

               return;
            case Kq:
               var1.pC.append("operator ");
               this.pC(var1, var2.wS.kS().A);
               return;
            case go:
               var1.pC.append("operator ");
               this.pC(var1, var2.pC());
               return;
            case JF:
               var1.pC.append("operator ");
               this.NS(var1, var2);
               return;
            case Nq:
               this.fI(var1, var2.pC());
               return;
            case pg:
               awe var26 = var2.pC();
               awe var47 = var2.A();
               String var59 = null;
               if (var26.pC == awg.kU) {
                  var59 = var26.wS.A().pC.pC;
                  if (var59.equals("ad") && var47.pC == awg.wS && var47.pC().pC == awg.A && var47.A().pC == awg.pF) {
                     var47 = var47.pC();
                  }

                  if (var47.pC == awg.ZD) {
                     var47 = var47.pC();
                     this.sY(var1, var47);
                     this.fI(var1, var26);
                     return;
                  }
               }

               if (var59 != null && var59.equals("sZ")) {
                  awe var67 = this.E(var1, var47);
                  int var11 = this.A(var67);
                  var1.pC.append(var11);
                  return;
               }

               if (var59 != null && var59.equals("sP")) {
                  int var66 = this.UT(var1, var47);
                  var1.pC.append(var66);
                  return;
               }

               if (var26.pC != awg.go) {
                  this.fI(var1, var26);
               } else {
                  var1.pC.append('(');
                  this.WR(var1, var26);
                  var1.pC.append(')');
               }

               if (var59 != null && var59.equals("gs")) {
                  this.pC(var1, var47);
               } else if (var59 == null
                  || !var59.equals("ti")
                     && !var59.equals("te")
                     && !var59.equals("st")
                     && !var59.equals("sz")
                     && !var59.equals("at")
                     && !var59.equals("az")
                     && !var59.equals("nx")) {
                  this.sY(var1, var47);
               } else {
                  var1.pC.append('(');
                  this.pC(var1, var47);
                  var1.pC.append(')');
               }

               return;
            case gj:
               if (var2.A().pC != awg.ZD) {
                  throw new RuntimeException("");
               }

               if (avv.pC(var2.pC())) {
                  this.fI(var1, var2.pC());
                  var1.pC.append('<');
                  this.pC(var1, var2.A().pC());
                  var1.pC.append(">(");
                  this.pC(var1, var2.A().A());
                  var1.pC.append(')');
                  return;
               }

               if (this.kS(var1, var2)) {
                  return;
               }

               if (this.wS(var1, var2)) {
                  return;
               }

               boolean var25 = false;
               if (var2.pC().pC == awg.kU && var2.pC().wS.A().pC.A.contains(">")) {
                  var1.pC.append('(');
                  var25 = true;
               }

               if (var2.pC().wS.A().pC.pC.equals("cl") && var2.A().pC().pC == awg.wS) {
                  awe var46 = var2.A().pC();
                  if (var46.A().pC != awg.pF) {
                     throw new RuntimeException();
                  }

                  this.sY(var1, var46.pC());
               } else {
                  this.sY(var1, var2.A().pC());
               }

               if (var2.pC().wS.A().pC.pC.equals("ix")) {
                  var1.pC.append('[');
                  this.pC(var1, var2.A().A());
                  var1.pC.append(']');
               } else {
                  if (!var2.pC().wS.A().pC.pC.equals("cl")) {
                     this.fI(var1, var2.pC());
                  }

                  this.sY(var1, var2.A().A());
               }

               if (var25) {
                  var1.pC.append(')');
               }

               return;
            case ZD:
               throw new RuntimeException();
            case DL:
               if (var2.A().pC == awg.UH && var2.A().A().pC == awg.VD) {
                  if (this.kS(var1, var2)) {
                     return;
                  }

                  if (this.wS(var1, var2)) {
                     return;
                  }

                  awe var24 = var2.pC();
                  awe var45 = var2.A().pC();
                  awe var58 = var2.A().A().pC();
                  awe var65 = var2.A().A().A();
                  if (var24.wS.A().pC.pC.equals("qu")) {
                     this.sY(var1, var45);
                     this.fI(var1, var24);
                     this.sY(var1, var58);
                     var1.pC.append(" : ");
                     this.sY(var1, var65);
                  } else {
                     var1.pC.append("new ");
                     if (var45.pC() != null) {
                        this.sY(var1, var45);
                        var1.pC.append(' ');
                     }

                     this.pC(var1, var58);
                     if (var65 != null) {
                        this.sY(var1, var65);
                     }
                  }

                  return;
               }

               throw new RuntimeException();
            case UH:
            case VD:
               throw new RuntimeException();
            case Xs:
            case KV:
               awd.Av var23 = awd.Av.pC;
               if (var2.pC().pC == awg.ZN) {
                  var23 = var2.pC().wS.E().pC.kS;
                  switch (var23) {
                     case kS:
                     case wS:
                     case UT:
                     case E:
                     case sY:
                     case A:
                        if (var2.A().pC == awg.pC) {
                           if (var2.pC == awg.KV) {
                              var1.pC.append('-');
                           }

                           this.pC(var1, var2.A());
                           switch (var23) {
                              case kS:
                                 var1.pC.append('u');
                                 break;
                              case wS:
                                 var1.pC.append('l');
                                 break;
                              case UT:
                                 var1.pC.append("ul");
                                 break;
                              case E:
                                 var1.pC.append("ll");
                                 break;
                              case sY:
                                 var1.pC.append("ull");
                           }

                           return;
                        }
                        break;
                     case ys:
                        if (var2.A().pC == awg.pC && var2.A().wS.pC().pC.length() == 1 && var2.pC == awg.Xs) {
                           switch (var2.A().wS.pC().pC.charAt(0)) {
                              case '0':
                                 var1.pC.append("false");
                                 return;
                              case '1':
                                 var1.pC.append("true");
                                 return;
                           }
                        }
                  }
               }

               var1.pC.append('(');
               this.pC(var1, var2.pC());
               var1.pC.append(')');
               if (var2.pC == awg.KV) {
                  var1.pC.append('-');
               }

               if (var23 == awd.Av.ld) {
                  var1.pC.append('[');
               }

               this.pC(var1, var2.A());
               if (var23 == awd.Av.ld) {
                  var1.pC.append(']');
               }

               return;
            case FK:
               this.pC(var1, var2.pC());
               var1.pC.append('(');
               this.pC(var1, var2.A());
               var1.pC.append(')');
               return;
            case Ip:
               var1.pC.append(var2.wS.ld().pC);
               return;
            case Bi:
               var1.pC.append("java resource ");
               this.pC(var1, var2.pC());
               return;
            case wQ:
               this.pC(var1, var2.pC());
               this.pC(var1, var2.A());
               return;
            case PZ:
               var1.pC.append(var2.wS.gp().pC);
               return;
            case Fm:
               var1.pC.append("decltype(");
               this.pC(var1, var2.pC());
               var1.pC.append(')');
               return;
            case So:
               awe var44 = null;
               if (var1.E == 0) {
                  var44 = this.E(var1, var2.pC());
               }

               if (var44 == null) {
                  this.sY(var1, var2.pC());
                  var1.pC.append("...");
               } else {
                  int var22 = this.A(var44);
                  var2 = var2.pC();

                  for (int var57 = 0; var57 < var22; var57++) {
                     if (var57 != 0) {
                        var1.pC.append(", ");
                     }

                     var1.sY = var57;
                     this.pC(var1, var2);
                  }
               }

               return;
            case sY:
               long var21 = var2.wS.ld().pC;
               if (var21 == 0L) {
                  var1.pC.append("this");
               } else {
                  var1.pC.append("{parm#");
                  var1.pC.append(var21);
                  var1.pC.append('}');
               }

               return;
            case FM:
               var1.pC.append("global constructors keyed to ");
               this.pC(var1, var2.pC());
               return;
            case Wn:
               var1.pC.append("global destructors keyed to ");
               this.pC(var1, var2.pC());
               return;
            case gy:
               var1.pC.append("{lambda");
               awe var20 = var2.wS.fI().pC;
               awa.Sv var43 = new awa.Sv();
               int var9 = var1.E;
               var1.E = 0;
               var43.A = null;
               var43.pC = var1.A;
               var1.A = var43;
               if (var20 != null && var20.pC == awg.be) {
                  var43.A = var20;
                  var1.pC.append('<');

                  for (awe var10 = var20.pC(); var10 != null; var10 = var10.A()) {
                     if (var1.E++ != 0) {
                        var1.pC.append(", ");
                     }

                     this.pC(var1, var10);
                     var1.pC.append(' ');
                     if (var10.pC == awg.eE) {
                        var10 = var10.pC();
                     }

                     this.pC(var1, var10.pC, var1.E - 1);
                  }

                  var1.pC.append('>');
                  var20 = var20.A();
               }

               var1.E++;
               var1.pC.append('(');
               this.pC(var1, var20);
               var1.E = var9;
               var1.A = var43.pC;
               var1.pC.append(")#");
               var1.pC.append(var2.wS.fI().A + 1);
               var1.pC.append('}');
               return;
            case uE:
               var1.pC.append("{unnamed type#");
               var1.pC.append(var2.wS.ld().pC + 1L);
               var1.pC.append('}');
               return;
            case Br:
               this.pC(var1, var2.pC());
               var1.pC.append(" [clone ");
               this.pC(var1, var2.A());
               var1.pC.append(']');
               return;
            case IE:
               this.pC(var1, var2.pC());
               var1.pC.append("[friend]");
               return;
            case be:
               var1.pC.append('<');
               int var7 = 0;

               for (awe var8 = var2.pC(); var8 != null; var8 = var8.A()) {
                  if (var7 != 0) {
                     var1.pC.append(", ");
                  }

                  var7++;
                  this.pC(var1, var8);
               }

               var1.pC.append('>');
               return;
            case Xh:
               var1.pC.append("typename");
               return;
            case sz:
               this.pC(var1, var2.pC());
               return;
            case QQ:
               var1.pC.append("template");
               this.pC(var1, var2.pC());
               var1.pC.append(" class");
               return;
            case eE:
               this.pC(var1, var2.pC());
               var1.pC.append("...");
               return;
            case dM:
               var1.pC.append("struct ");
               this.pC(var1, var2.pC());
               return;
            case EM:
               var1.pC.append("union ");
               this.pC(var1, var2.pC());
               return;
            case fD:
               var1.pC.append("enum ");
               this.pC(var1, var2.pC());
               return;
            case ii:
               this.pC(var1, var2.pC());
               var1.pC.append(" requires ");
               this.pC(var1, var2.A());
               return;
            default:
               throw new RuntimeException();
         }

         awa.Av var36 = new awa.Av();
         var36.pC = var1.kS;
         var1.kS = var36;
         var36.A = var2;
         var36.kS = 0;
         var36.wS = var1.A;
         if (var3 == null) {
            var3 = var2.pC();
         }

         this.pC(var1, var3);
         if (var36.kS == 0) {
            this.oT(var1, var2);
         }

         var1.kS = var36.pC;
         if (var5) {
            var1.A = var4;
         }
      }
   }

   private boolean kS(awa var1, awe var2) {
      String var8 = var2.pC().wS.A().pC.pC;
      if (!var8.startsWith("f")) {
         return false;
      } else {
         awe var3 = var2.A();
         awe var4 = var3.pC();
         awe var5 = var3.A();
         awe var6 = null;
         if (var5.pC == awg.VD) {
            var6 = var5.A();
            var5 = var5.pC();
         }

         int var7 = var1.sY;
         var1.sY = -1;
         switch (var8.charAt(1)) {
            case 'L':
            case 'R':
               var1.pC.append('(');
               this.sY(var1, var5);
               this.fI(var1, var4);
               var1.pC.append("...");
               this.fI(var1, var4);
               this.sY(var1, var6);
               var1.pC.append(')');
               break;
            case 'l':
               var1.pC.append("(...");
               this.fI(var1, var4);
               this.sY(var1, var5);
               var1.pC.append(')');
               break;
            case 'r':
               var1.pC.append('(');
               this.sY(var1, var5);
               this.fI(var1, var4);
               var1.pC.append("...)");
               break;
            default:
               throw new RuntimeException();
         }

         var1.sY = var7;
         return true;
      }
   }

   private boolean pC(awe var1) {
      if (var1.pC != awg.gj && var1.pC != awg.DL) {
         return false;
      } else {
         awe var2 = var1.pC();
         String var3 = var2.wS.A().pC.pC;
         return var3.charAt(0) == 'd' && (var3.charAt(1) == 'i' || var3.charAt(1) == 'x' || var3.charAt(1) == 'X');
      }
   }

   private boolean wS(awa var1, awe var2) {
      if (!this.pC(var2)) {
         return false;
      } else {
         String var3 = var2.pC().wS.A().pC.pC;
         awe var4 = var2.A();
         awe var5 = var4.pC();
         awe var6 = var4.A();
         if (var3.charAt(1) == 'i') {
            var1.pC.append('.');
         } else {
            var1.pC.append('[');
         }

         this.pC(var1, var5);
         if (var3.charAt(1) == 'X') {
            var1.pC.append(" ... ");
            this.pC(var1, var6.pC());
            var6 = var6.A();
         }

         if (var3.charAt(1) != 'i') {
            var1.pC.append(']');
         }

         if (this.pC(var6)) {
            this.pC(var1, var6);
         } else {
            var1.pC.append('=');
            this.sY(var1, var6);
         }

         return true;
      }
   }

   private void pC(awa var1, awg var2, long var3) {
      var1.pC.append(switch (var2) {
         case Xh -> "$T";
         case sz -> "$N";
         case QQ -> "$TT";
         default -> {
            var1.wS = true;
            yield "";
         }
      });
      var1.pC.append(var3);
   }

   private int UT(awa var1, awe var2) {
      int var3 = 0;

      while (var2 != null && var2.pC == awg.RW) {
         awe var4 = var2.pC();
         if (var4 == null) {
            break;
         }

         if (var4.pC == awg.So) {
            awe var5 = this.E(var1, var2.pC());
            var3 += this.A(var5);
         } else {
            var3++;
         }

         var2 = var2.A();
      }

      return var3;
   }

   private int A(awe var1) {
      int var2 = 0;

      while (var1 != null && var1.pC == awg.RW && var1.pC() != null) {
         var2++;
         var1 = var1.A();
      }

      return var2;
   }

   private awe E(awa var1, awe var2) {
      if (var2 == null) {
         return null;
      } else {
         switch (var2.pC) {
            case pC:
            case tH:
            case Ek:
            case ZN:
            case Gu:
            case Bf:
            case kU:
            case Ip:
            case PZ:
            case sY:
            case gy:
            case uE:
            case pt:
               return null;
            case E:
               awe var5;
               try {
                  var5 = this.gp(var1, var2);
               } catch (awi var4) {
                  return null;
               }

               if (var5 != null && var5.pC == awg.RW) {
                  return var5;
               }

               return null;
            case ys:
               return this.E(var1, var2.wS.wS().A);
            case ld:
               return this.E(var1, var2.wS.UT().A);
            case Kq:
               return this.E(var1, var2.wS.kS().A);
            case So:
               return null;
            default:
               awe var3 = this.E(var1, var2.pC());
               return var3 != null ? var3 : this.E(var1, var2.A());
         }
      }
   }

   private void sY(awa var1, awe var2) {
      boolean var3 = false;
      awg var4 = var2.pC;
      if (var2.pC == awg.E) {
         try {
            awe var5 = this.gp(var1, var2);
            if (var5 != null) {
               var4 = var5.pC;
            }
         } catch (awi var6) {
         }
      }

      if (var4 == awg.pC || var4 == awg.A || var4 == awg.xM || var4 == awg.sY || var4 == awg.Xs || var4 == awg.KV) {
         var3 = true;
      }

      if (!var3) {
         var1.pC.append('(');
      }

      this.pC(var1, var2);
      if (!var3) {
         var1.pC.append(')');
      }
   }

   private void ys(awa var1, awe var2) {
      awa.K var3 = new awa.K();
      var3.pC = var2;
      awa.Sv var4 = var1.A;
      if (var4 != null) {
         var3.A = new awa.Sv();
         var3.A.A = var4.A;
         var4 = var4.pC;

         for (awa.Sv var5 = var3.A; var4 != null; var4 = var4.pC) {
            awa.Sv var6 = new awa.Sv();
            var6.A = var4.A;
            var5.pC = var6;
            var5 = var6;
         }

         var1.gp.add(var3);
      }
   }

   private awa.K ld(awa var1, awe var2) {
      for (awa.K var4 : var1.gp) {
         if (var4.pC == var2) {
            return var4;
         }
      }

      return null;
   }

   private awe gp(awa var1, awe var2) throws awi {
      if (var1.A != null && var1.A.A != null) {
         return this.pC(var1.A.A.A(), (int)var2.wS.ld().pC);
      } else {
         throw new awi();
      }
   }

   private awe pC(awe var1, int var2) {
      if (var2 < 0) {
         return var1;
      } else {
         awe var3;
         for (var3 = var1; var3 != null; var3 = var3.A()) {
            if (var3.pC != awg.RW) {
               return null;
            }

            if (var2 <= 0) {
               break;
            }

            var2--;
         }

         return var2 == 0 && var3 != null ? var3.pC() : null;
      }
   }

   private void oT(awa var1, awe var2) {
      switch (var2.pC) {
         case wS:
            this.pC(var1, var2.pC());
            return;
         case UT:
         case E:
         case e:
         case ys:
         case ld:
         case rC:
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
         case UO:
         case Ab:
         case rl:
         case z:
         case Um:
         case Ta:
         case Ek:
         case ZN:
         case Gu:
         case OB:
         case pF:
         case Bc:
         default:
            this.pC(var1, var2);
            return;
         case hK:
         case Aj:
            var1.pC.append(" restrict");
            return;
         case Er:
         case EX:
            var1.pC.append(" volatile");
            return;
         case FE:
         case LM:
            var1.pC.append(" const");
            return;
         case Cu:
            var1.pC.append(' ');
            this.pC(var1, var2.A());
            return;
         case hZ:
            var1.pC.append('*');
            return;
         case cX:
            var1.pC.append(" _Complex");
            return;
         case DQ:
            var1.pC.append(" _Imaginary");
            return;
         case mv:
            var1.pC.append(' ');
         case UW:
            var1.pC.append('&');
            return;
         case sO:
            var1.pC.append(' ');
         case PR:
            var1.pC.append("&&");
            return;
         case os:
            return;
         case Gm:
            var1.pC.append(" transaction_safe");
            return;
         case AU:
            var1.pC.append(" noexcept");
            if (var2.A() != null) {
               var1.pC.append('(');
               this.pC(var1, var2.A());
               var1.pC.append(')');
            }

            return;
         case jS:
            var1.pC.append(" throw");
            if (var2.A() != null) {
               var1.pC.append('(');
               this.pC(var1, var2.A());
               var1.pC.append(')');
            }

            return;
         case OI:
            if (var1.pC.charAt(var1.pC.length() - 1) != '(') {
               var1.pC.append(' ');
            }

            this.pC(var1, var2.pC());
            var1.pC.append("::*");
            return;
         case Pe:
            var1.pC.append(" __vector(");
            this.pC(var1, var2.pC());
            var1.pC.append(')');
      }
   }

   private void pC(awa var1, awe var2, awa.Av var3) {
      boolean var4 = false;
      boolean var5 = false;
      boolean var6 = false;

      for (awa.Av var7 = var3; var7 != null && var7.A != null && var7.kS == 0; var7 = var7.pC) {
         switch (var7.A.pC) {
            case hK:
            case Er:
            case FE:
            case Cu:
            case cX:
            case DQ:
            case OI:
               var5 = true;
               var4 = true;
               break;
            case UW:
            case PR:
            case hZ:
               var4 = true;
            case Aj:
            case EX:
            case LM:
            case mv:
            case sO:
            case Gm:
            case AU:
            case jS:
            case ZN:
            case Gu:
            case OB:
            case pF:
            case Bc:
            default:
               break;
            case os:
               var6 = true;
         }

         if (var4) {
            break;
         }
      }

      if (var4) {
         if (!var5 && var1.pC.charAt(var1.pC.length() - 1) != '(' && var1.pC.charAt(var1.pC.length() - 1) != '*') {
            var5 = true;
         }

         if (var5 && var1.pC.charAt(var1.pC.length() - 1) != ' ') {
            var1.pC.append(' ');
         }

         var1.pC.append('(');
      }

      awa.Av var8 = var1.kS;
      var1.kS = null;
      this.pC(var1, var3, false);
      if (var4) {
         var1.pC.append(')');
      }

      var1.pC.append('(');
      if (var6) {
         var1.pC.append("this ");
      }

      if (var2.A() != null) {
         this.pC(var1, var2.A());
      }

      var1.pC.append(')');
      this.pC(var1, var3, true);
      var1.kS = var8;
   }

   private void pC(awa var1, awa.Av var2, boolean var3) {
      if (var2 != null && var2.A != null && !var1.wS) {
         if (var2.kS == 0 && (var3 || !avv.pC(var2.A.pC))) {
            var2.kS = 1;
            awa.Sv var4 = var1.A;
            var1.A = var2.wS;
            if (var2.A.pC == awg.pF) {
               this.pC(var1, var2.A, var2.pC);
               var1.A = var4;
            } else if (var2.A.pC == awg.Bc) {
               this.A(var1, var2.A, var2.pC);
               var1.A = var4;
            } else if (var2.A.pC == awg.kS) {
               awa.Av var5 = var1.kS;
               var1.kS = null;
               this.pC(var1, var2.A.pC());
               var1.kS = var5;
               var1.pC.append("::");
               awe var6 = var2.A.A();
               awe var7 = var6;
               int var8 = 0;
               if (var6.UT) {
                  var8 = var1.pC.length();
               }

               if (var6.pC == awg.pt) {
                  var1.pC.append("{default arg#");
                  var1.pC.append(var6.wS.fI().A + 1);
                  var1.pC.append("}::");
                  var7 = var6.wS.fI().pC;
               }

               while (avv.pC(var7.pC)) {
                  var7 = var7.pC();
               }

               this.pC(var1, var7);
               if (var6.UT && var6.pC == awg.pt) {
                  var6.E = var1.pC.substring(var8).toString();
               }

               var1.A = var4;
            } else if (var2.A.pC != awg.jS && var2.A.pC != awg.AU) {
               this.oT(var1, var2.A);
               var1.A = var4;
               this.pC(var1, var2.pC, var3);
            } else {
               this.pC(var1, var2.pC, var3);
               this.oT(var1, var2.A);
               var1.A = var4;
            }
         } else {
            this.pC(var1, var2.pC, var3);
         }
      }
   }

   private void A(awa var1, awe var2, awa.Av var3) {
      boolean var4 = true;
      if (var3 != null) {
         boolean var5 = false;

         for (awa.Av var6 = var3; var6 != null && var6.A != null; var6 = var6.pC) {
            if (var6.kS == 0) {
               if (var6.A.pC == awg.Bc) {
                  var4 = false;
               } else {
                  var5 = true;
                  var4 = true;
               }
               break;
            }
         }

         if (var5) {
            var1.pC.append(" (");
         }

         this.pC(var1, var3, false);
         if (var5) {
            var1.pC.append(')');
         }
      }

      if (var4) {
         var1.pC.append(' ');
      }

      var1.pC.append('[');
      if (var2.pC() != null) {
         this.pC(var1, var2.pC());
      }

      var1.pC.append(']');
   }

   private void fI(awa var1, awe var2) {
      if (var2.pC == awg.kU) {
         var1.pC.append(var2.wS.A().pC.A);
      } else {
         this.pC(var1, var2);
      }
   }

   private void WR(awa var1, awe var2) {
      this.pC(var1, var2.pC());
   }

   private void NS(awa var1, awe var2) {
      awa.Sv var3 = new awa.Sv();
      if (var1.fI != null) {
         var3.pC = var1.A;
         var1.A = var3;
         var3.A = var1.fI;
      }

      if (var2.pC().pC != awg.UT) {
         this.pC(var1, var2.pC());
         if (var1.fI != null) {
            var1.A = var3.pC;
         }
      } else {
         this.pC(var1, var2.pC().pC());
         if (var1.fI != null) {
            var1.A = var3.pC;
         }

         if (var1.pC.charAt(var1.pC.length() - 1) == '<') {
            var1.pC.append(' ');
         }

         var1.pC.append('<');
         int var4 = var1.pC.length();
         this.pC(var1, var2.pC().A());
         if (var1.pC.charAt(var1.pC.length() - 1) == '>') {
            var1.pC.append(' ');
         }

         if (var1.pC.length() - var4 == 0) {
            var1.pC.deleteCharAt(var4 - 1);
         } else if (var1.pC.length() - var4 == 1 && var1.pC.charAt(var4) == '*') {
            var1.pC.delete(var4 - 1, var4 + 1);
         } else if (var1.pC.length() - var4 == 6 && "(*)...".equals(var1.pC.substring(var4, var4 + 6))) {
            var1.pC.delete(var4 - 1, var4 + 6);
         } else {
            var1.pC.append('>');
         }
      }
   }
}
