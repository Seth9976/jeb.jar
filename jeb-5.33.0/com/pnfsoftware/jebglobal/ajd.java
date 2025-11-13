package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeDecompilerUnit;
import com.pnfsoftware.jeb.util.collect.Maps;
import com.pnfsoftware.jeb.util.collect.MultiMap;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import com.pnfsoftware.jeb.util.primitives.Integers;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class ajd {
   private static final StructuredLogger kS = aco.pC(ajd.class);
   private int wS = 3;
   private aix UT;
   private aiw E;
   private IERoutineContext sY;
   private aiv ys;
   private int[][] ld;
   private MultiMap gp;
   private Map oT;
   private Map fI;
   public boolean pC = false;
   public static int A = 10;

   public ajd(aiw var1, IERoutineContext var2, byte[] var3, INativeDecompilerUnit var4) {
      this.E = var1;
      this.sY = var2;
      if (var4 != null) {
         String var5 = var4.getFormatType();
         if (var5.contains(ckx.pC(new byte[]{-51, 5}, 1, 161)) && var5.contains(ckx.pC(new byte[]{74, 23, 27}, 1, 43))) {
            A = 10;
         }
      }

      this.ys = this.E.ys();
      this.ld = new int[this.ys.pC.size()][];
      int var11 = 0;

      for (aiv.Av var7 : this.ys.pC) {
         int var8 = var7.pC.kS();
         this.ld[var11] = new int[var8];

         for (int var9 = 0; var9 < var8; var9++) {
            this.ld[var11][var9] = 0;
         }

         var11++;
      }

      int var12 = 0;
      if (var3 != null) {
         int var16 = 1;
         int var17 = 0;

         for (int var18 = 0; var18 < var3.length; var18++) {
            int var10 = var3[var18] & 255;
            var16 = (var16 + var10) % 65521;
            var17 = (var17 + var16) % 65521;
         }

         var12 = var17 << 16 | var16;
      }

      var12 += var1.kS() * var1.wS() % 10;
      var12 &= 255;
      var12 %= 10;
      if (var12 >= A) {
         this.gp = new MultiMap();
         this.oT = new HashMap();
         this.fI = new HashMap();
         this.pC = true;
      }
   }

   public void pC(int var1) {
      if (var1 <= 0 || var1 > 3) {
         var1 = 3;
      }

      this.wS = var1;
   }

   public int pC() {
      return this.wS;
   }

   public void pC(aix var1) {
      this.UT = var1;
   }

   public aje A() {
      this.E();
      this.wS();
      this.UT();
      return new aje(this.fI, this.gp, this.oT);
   }

   void kS() {
      if (this.gp == null) {
         this.gp = new MultiMap();
         List var1 = this.E.gp();
         int var2 = 0;

         for (aiv.Av var4 : this.ys.pC) {
            aiw var5 = var4.pC;

            for (List var7 : var4.A.pC) {
               try {
                  int var8 = (Integer)var7.get(0);
                  int var9 = 0;
                  ArrayList var10 = new ArrayList();

                  for (int var12 : var5.A(var8)) {
                     if (var7.contains(var12)) {
                        var10.add(var12);
                        if (var12 > var9) {
                           var9 = var12;
                        }
                     }
                  }

                  if (var9 > 0) {
                     int[] var36 = new int[]{var8, var9};
                     this.pC(this.ys, var2, var36);
                     int var37 = var36[0];
                     int var13 = var36[1];
                     if (var13 < var37) {
                        throw new RuntimeException("latch located before header");
                     }

                     if (var37 > 0 && var13 > 0 && (this.ld[0][var37 - 1] == 0 || this.ld[0][var13 - 1] == 0)) {
                        ArrayList var14 = new ArrayList();

                        for (int var15 = var37; var15 <= var13; var15++) {
                           var14.add(var15);
                        }

                        int var38 = this.E.UT(var37).size();
                        int var16 = this.E.UT(var13).size();
                        if (var38 <= 0 || var16 <= 0) {
                           throw new RuntimeException("illegal orders");
                        }

                        if (var38 <= 2 || var16 <= 2) {
                           boolean var17 = false;
                           boolean var18 = false;
                           HashSet var19 = new HashSet();
                           HashSet var20 = new HashSet();
                           HashMap var21 = new HashMap();

                           for (int var23 : var14) {
                              for (int var25 : this.E.UT(var23)) {
                                 if (!var14.contains(var25)) {
                                    var19.add(var23);
                                    var20.add(var25);
                                    Maps.putMulti(var21, var23, var25);
                                    if (var23 == var37 || var23 == var13) {
                                       if (var23 == var37) {
                                          var17 = true;
                                       }

                                       if (var23 == var13) {
                                          var18 = true;
                                       }
                                    }
                                 }
                              }
                           }

                           Set var39 = null;

                           for (int var42 : var19) {
                              Set var44 = (Set)var1.get(var42 - 1);
                              if (var39 == null) {
                                 var39 = var44;
                              } else {
                                 var39.retainAll(var44);
                              }
                           }

                           Integer var41 = null;
                           if (var39 != null) {
                              for (int var45 : new TreeSet(var39)) {
                                 if (var45 > var37 && var45 > var13) {
                                    var41 = var45;
                                    break;
                                 }
                              }
                           }

                           Object[] var10000 = new Object[]{var41};
                           if (var20.isEmpty()) {
                              var10000 = new Object[0];
                           } else if (var20.size() == 1) {
                              var10000 = new Object[]{var20.iterator().next()};
                           } else {
                              var10000 = new Object[]{var20};
                           }

                           int var46 = 0;
                           int var26 = 0;
                           aja var28 = null;
                           aja var27;
                           if (!var17 && !var18) {
                              var27 = aja.kS;
                           } else if (!var17 && var18) {
                              var27 = aja.A;
                           } else if (var17 && !var18) {
                              var27 = aja.pC;
                           } else {
                              if (((List)var21.get(var37)).equals(var21.get(var13))) {
                                 var27 = aja.A;
                              } else {
                                 var27 = aja.A;
                                 if (var41 != null) {
                                    var26 = var41;
                                 } else {
                                    int var29 = (Integer)((List)var21.get(var37)).get(0);
                                    int var30 = (Integer)((List)var21.get(var13)).get(0);
                                    var26 = Math.max(var29, var30);
                                 }
                              }

                              var28 = aja.pC;
                              if (this.UT != null && this.UT.pC(var8)) {
                                 var27 = aja.pC;
                                 var28 = aja.A;
                              }
                           }

                           if (var27 == aja.pC) {
                              if (this.ld[0][var37 - 1] != 0) {
                                 var27 = var28;
                              }
                           } else if (var27 == aja.A) {
                              if (this.ld[0][var13 - 1] != 0) {
                                 var27 = var28;
                              }
                           } else if (var27 == aja.kS && (this.ld[0][var37 - 1] != 0 || this.ld[0][var13 - 1] != 0)) {
                              var27 = var28;
                           }

                           if (var27 != null) {
                              if (var27 == aja.pC) {
                                 var46 = (Integer)((List)var21.get(var37)).get(0);
                              } else if (var27 == aja.A) {
                                 var46 = (Integer)((List)var21.get(var13)).get(0);
                              }

                              byte var47 = 1;
                              byte var48 = 1;
                              if (this.ld[0][var37 - 1] <= 0 && var27 == aja.A) {
                                 int var31 = Math.max(var46, var26);
                                 if (var31 > 0) {
                                    boolean var32 = false;

                                    for (int var34 : this.E.UT(var37)) {
                                       if (var34 >= var31) {
                                          var32 = true;
                                          break;
                                       }
                                    }

                                    if (!var32) {
                                       var47 = -1;
                                    }
                                 }
                              }

                              this.ld[0][var37 - 1] = var47;
                              this.ld[0][var13 - 1] = var48;
                              this.gp.put(var37, new aiz(var37, var13, var46, var27, var26));
                           }
                        }
                     }
                  }
               } catch (Exception var35) {
                  acj.pC(var35, this.sY);
               }
            }

            var2++;
         }
      }
   }

   void wS() {
      if (this.pC() >= 3) {
         this.kS();
      } else if (this.gp == null) {
         this.gp = new MultiMap();
         int var1 = 0;

         for (aiv.Av var3 : this.ys.pC) {
            aiw var4 = var3.pC;

            for (List var6 : var3.A.pC) {
               try {
                  int var7 = (Integer)var6.get(0);
                  int var8 = 0;
                  ArrayList var9 = new ArrayList();

                  for (int var11 : var4.A(var7)) {
                     if (var6.contains(var11)) {
                        var9.add(var11);
                        if (var11 > var8) {
                           var8 = var11;
                        }
                     }
                  }

                  if (var8 > 0) {
                     if (var8 < var7) {
                        throw new RuntimeException();
                     }

                     int[] var22 = new int[]{var7, var8};
                     this.pC(this.ys, var1, var22);
                     int var23 = var22[0];
                     int var12 = var22[1];
                     if (var23 > 0 && var12 > 0 && (this.ld[0][var23 - 1] == 0 || this.ld[0][var12 - 1] == 0)) {
                        ArrayList var13 = new ArrayList();

                        for (int var14 = var23; var14 <= var12; var14++) {
                           var13.add(var14);
                        }

                        int var24 = this.E.UT(var23).size();
                        int var15 = this.E.UT(var12).size();
                        if (var24 <= 0 || var15 <= 0) {
                           throw new RuntimeException();
                        }

                        if (var24 <= 2 || var15 <= 2) {
                           boolean var16 = false;
                           if (var24 >= 2) {
                              for (int var18 : this.E.UT(var23)) {
                                 if (!var13.contains(var18)) {
                                    var16 = true;
                                    break;
                                 }
                              }
                           }

                           aja var26 = null;
                           aja var25;
                           if (var24 == 1 && var15 == 1) {
                              var25 = aja.kS;
                           } else if (var24 == 1 && var15 >= 2) {
                              var25 = aja.A;
                           } else if (var24 >= 2 && var15 == 1) {
                              var25 = var16 ? aja.pC : aja.kS;
                           } else if (!var16) {
                              var25 = aja.A;
                           } else {
                              var25 = aja.A;
                              var26 = aja.pC;
                              if (this.UT != null && this.UT.pC(var7)) {
                                 var25 = aja.pC;
                                 var26 = aja.A;
                              }
                           }

                           if (var25 == aja.pC) {
                              if (this.ld[0][var23 - 1] != 0) {
                                 var25 = var26;
                              }
                           } else if (var25 == aja.A) {
                              if (this.ld[0][var12 - 1] != 0) {
                                 var25 = var26;
                              }
                           } else if (var25 == aja.kS && (this.ld[0][var23 - 1] != 0 || this.ld[0][var12 - 1] != 0)) {
                              var25 = var26;
                           }

                           if (var25 != null) {
                              int var19 = 0;
                              if (var25 == aja.A) {
                                 List var20 = this.E.UT(var12);
                                 if (var20.size() == 2) {
                                    if ((Integer)var20.get(0) == var23) {
                                       var19 = (Integer)var20.get(1);
                                    } else {
                                       if ((Integer)var20.get(1) != var23) {
                                          throw new RuntimeException("Cannot determine the follow node of a post-tested loop");
                                       }

                                       var19 = (Integer)var20.get(0);
                                    }
                                 } else {
                                    var25 = aja.kS;
                                 }
                              } else if (var25 == aja.pC) {
                                 List var27 = this.E.UT(var23);
                                 if (var27.size() == 2) {
                                    if (var13.contains(var27.get(0))) {
                                       var19 = (Integer)var27.get(1);
                                    } else {
                                       if (!var13.contains(var27.get(1))) {
                                          throw new RuntimeException("Cannot determine the follow node of a pre-tested loop");
                                       }

                                       var19 = (Integer)var27.get(0);
                                    }
                                 } else {
                                    var25 = aja.kS;
                                 }
                              } else {
                                 var19 = 0;
                              }

                              if (this.ld[0][var23 - 1] <= 0 && var25 == aja.A) {
                                 this.ld[0][var23 - 1] = -1;
                              } else {
                                 this.ld[0][var23 - 1] = 1;
                              }

                              this.ld[0][var12 - 1] = 1;
                              if (var25 == aja.pC && var19 > var12) {
                                 var12 = var19 - 1;
                              }

                              this.gp.put(var23, new aiz(var23, var12, var19, var25));
                           }
                        }
                     }
                  }
               } catch (Exception var21) {
                  acj.pC(var21, this.sY);
               }
            }

            var1++;
         }
      }
   }

   private void pC(aiv var1, int var2, int[] var3) {
      int var4 = var3[0];
      int var5 = var3[1];

      while (var2 >= 1) {
         aiv.Av var6 = (aiv.Av)var1.pC.get(--var2);
         aiw var7 = var6.pC;
         var4 = (Integer)((List)var6.A.pC.get(var4 - 1)).get(0);
         int var8 = 0;

         for (int var10 : (List)var6.A.pC.get(var5 - 1)) {
            if (var7.UT(var10).contains(var4) && var10 > var8) {
               var8 = var10;
            }
         }

         var5 = var8;
      }

      var3[0] = var4;
      var3[1] = var5;
   }

   void UT() {
      if (this.pC() >= 2) {
         this.sY();
      } else if (this.oT == null) {
         this.oT = new HashMap();
         List var1 = this.E.A(false);
         HashSet var2 = new HashSet();

         for (int var3 = this.E.kS(); var3 >= 1; var3--) {
            if (this.E.UT(var3).size() == 2 && this.ld[0][var3 - 1] <= 0) {
               int var4 = 0;
               int var5 = 1;

               for (int var7 : var1) {
                  if (var7 == var3 && this.E.A(var5).size() >= 2 && var5 > var4) {
                     var4 = var5;
                  }

                  var5++;
               }

               if (var4 <= var3) {
                  var2.add(var3);
               } else {
                  this.ld[0][var3 - 1] = 1;
                  this.oT.put(var3, new aiu(var3, var4));

                  for (int var9 : var2) {
                     if (var9 < var4 && this.E.pC(var3, var9)) {
                        this.ld[0][var9 - 1] = 1;
                        this.oT.put(var9, new aiu(var9, var4));
                     }
                  }

                  var2.clear();
               }
            }
         }
      }
   }

   void E() {
      if (this.pC() >= 2) {
         this.ys();
      } else if (this.fI == null) {
         this.fI = new HashMap();
         List var1 = this.E.A(false);
         HashSet var2 = new HashSet();

         for (int var3 = this.E.kS(); var3 >= 1; var3--) {
            int var4 = this.E.UT(var3).size();
            if (var4 >= 3 && this.ld[0][var3 - 1] <= 0) {
               int var5 = 0;
               int var6 = 1;

               for (int var8 : var1) {
                  if (var8 == var3 && this.E.A(var6).size() >= var4 && var6 > var5) {
                     var5 = var6;
                  }

                  var6++;
               }

               if (var5 <= var3) {
                  var2.add(var3);
               } else {
                  this.ld[0][var3 - 1] = 1;
                  this.fI.put(var3, new aiu(var3, var5));

                  for (int var10 : var2) {
                     if (var10 < var5 && this.E.pC(var3, var10)) {
                        this.ld[0][var10 - 1] = 1;
                        this.fI.put(var10, new aiu(var10, var5));
                     }
                  }

                  var2.clear();
               }
            }
         }
      }
   }

   private void sY() {
      this.pC(false);
   }

   private void ys() {
      this.pC(true);
   }

   private void pC(boolean var1) {
      Map var2 = var1 ? this.fI : this.oT;
      if (var2 == null) {
         int var4 = this.E.A().size();
         List var3;
         if (var4 <= 1) {
            var3 = this.E.pC(false, true, 0);
         } else {
            var3 = this.E.kS(false);
         }

         HashMap var17 = new HashMap();
         if (var1) {
            this.fI = var17;
         } else {
            this.oT = var17;
         }

         long var5 = 0L;

         for (int var7 = this.E.kS(); var7 >= 1; var7--) {
            List var8 = this.E.UT(var7);
            int var9 = var8.size();
            if ((var1 && var9 > 2 || !var1 && var9 == 2) && this.ld[0][var7 - 1] <= 0) {
               int var10 = Integers.min(var8);
               Set var11 = (Set)var3.get(var7 - 1);
               Integer var12 = null;

               for (Integer var14 : var11) {
                  if (var14 >= var10 && (var12 == null || var14 < var12)) {
                     var12 = var14;
                  }
               }

               if (var12 == null) {
                  if (var5 > 6000L) {
                     acj.pC(new RuntimeException("structureC abort"), this.sY);
                     break;
                  }

                  long var19 = System.currentTimeMillis();
                  var11 = (Set)this.E.pC(false, true, var7).get(var7 - 1);
                  var5 += System.currentTimeMillis() - var19;

                  for (Integer var16 : var11) {
                     if (var16 >= var10 && (var12 == null || var16 < var12)) {
                        var12 = var16;
                     }
                  }
               }

               if (var12 != null && var12 > var7) {
                  boolean var20 = true;
                  Set var22 = this.E.A(var7, var12);
                  aiw var23 = new aiw(this.E);
                  var23.ld(var7);
                  if (var7 != 1) {
                     Set var24 = var23.ys(1);
                     var20 = Collections.disjoint(var24, var22);
                  }

                  if (var20) {
                     Set var25 = var23.ys(var12);
                     var20 = Collections.disjoint(var25, var22);
                     if (var20) {
                        this.ld[0][var7 - 1] = 1;
                        var17.put(var7, new aiu(var7, var12));
                     }
                  }
               }
            }
         }
      }
   }
}
