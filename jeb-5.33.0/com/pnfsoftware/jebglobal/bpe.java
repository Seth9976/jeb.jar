package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.collect.Maps;
import com.pnfsoftware.jeb.util.collect.MultiMap;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.primitives.Integers;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class bpe {
   private static final ILogger kS = GlobalLog.getLogger(bpe.class);
   private int wS = 3;
   private box UT;
   private bou E;
   private String sY;
   private bot ys;
   private int[][] ld;
   private MultiMap gp;
   private Map oT;
   private Map fI;
   public boolean pC = false;
   public static int A = 10;

   public bpe(bou var1, String var2, byte[] var3) {
      this.E = var1;
      this.sY = var2;
      this.ys = this.E.UT();
      this.ld = new int[this.ys.pC.size()][];
      int var4 = 0;

      for (bot.Av var6 : this.ys.pC) {
         int var7 = var6.pC.A();
         this.ld[var4] = new int[var7];

         for (int var8 = 0; var8 < var7; var8++) {
            this.ld[var4][var8] = 0;
         }

         var4++;
      }

      int var10 = 0;
      if (var3 != null) {
         int var14 = 1;
         int var15 = 0;

         for (int var16 = 0; var16 < var3.length; var16++) {
            int var9 = var3[var16] & 255;
            var14 = (var14 + var9) % 65521;
            var15 = (var15 + var14) % 65521;
         }

         var10 = var15 << 16 | var14;
      }

      var10 += var1.A() * var1.kS() % 10;
      var10 &= 255;
      var10 %= 10;
      if (var10 >= A) {
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

   public void pC(box var1) {
      this.UT = var1;
   }

   public bpg A() {
      this.E();
      this.wS();
      this.UT();
      return new bpg(this.gp, this.oT, this.fI);
   }

   void kS() {
      if (this.gp == null) {
         this.gp = new MultiMap();
         List var1 = this.E.E();
         int var2 = 0;

         for (bot.Av var4 : this.ys.pC) {
            bou var5 = var4.pC;

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
                     int[] var37 = new int[]{var8, var9};
                     this.pC(this.ys, var2, var37);
                     int var38 = var37[0];
                     int var13 = var37[1];
                     if (var13 < var38) {
                        throw new RuntimeException("latch located before header");
                     }

                     if (var38 > 0 && var13 > 0 && (this.ld[0][var38 - 1] == 0 || this.ld[0][var13 - 1] == 0)) {
                        ArrayList var14 = new ArrayList();

                        for (int var15 = var38; var15 <= var13; var15++) {
                           var14.add(var15);
                        }

                        boolean var39 = true;
                        int var16 = this.E.wS(var38).size();
                        int var17 = this.E.wS(var13).size();
                        if (var16 <= 0 || var17 <= 0) {
                           var39 = false;
                        } else if (var16 > 2 && var17 > 2) {
                           var39 = false;
                        }

                        if (var39) {
                           boolean var18 = false;
                           boolean var19 = false;
                           HashSet var20 = new HashSet();
                           HashSet var21 = new HashSet();
                           HashMap var22 = new HashMap();

                           for (int var24 : var14) {
                              for (int var26 : this.E.wS(var24)) {
                                 if (!var14.contains(var26)) {
                                    var20.add(var24);
                                    var21.add(var26);
                                    Maps.putMulti(var22, var24, var26);
                                    if (var24 == var38 || var24 == var13) {
                                       if (var24 == var38) {
                                          var18 = true;
                                       }

                                       if (var24 == var13) {
                                          var19 = true;
                                       }
                                    }
                                 }
                              }
                           }

                           Set var40 = null;

                           for (int var43 : var20) {
                              Set var45 = (Set)var1.get(var43 - 1);
                              if (var40 == null) {
                                 var40 = var45;
                              } else {
                                 var40.retainAll(var45);
                              }
                           }

                           Integer var42 = null;
                           if (var40 != null) {
                              for (int var46 : new TreeSet(var40)) {
                                 if (var46 > var38 && var46 > var13) {
                                    var42 = var46;
                                    break;
                                 }
                              }
                           }

                           Object[] var10000 = new Object[]{var42};
                           if (var21.isEmpty()) {
                              var10000 = new Object[0];
                           } else if (var21.size() == 1) {
                              var10000 = new Object[]{var21.iterator().next()};
                           } else {
                              var10000 = new Object[]{var21};
                           }

                           int var47 = -1;
                           int var27 = -1;
                           bpb var29 = null;
                           bpb var28;
                           if (!var18 && !var19) {
                              var28 = bpb.kS;
                           } else if (!var18 && var19) {
                              var28 = bpb.A;
                           } else if (var18 && !var19) {
                              var28 = bpb.pC;
                           } else {
                              if (((List)var22.get(var38)).equals(var22.get(var13))) {
                                 var28 = bpb.A;
                              } else {
                                 var28 = bpb.A;
                                 if (var42 != null) {
                                    var27 = var42;
                                 } else {
                                    int var30 = (Integer)((List)var22.get(var38)).get(0);
                                    int var31 = (Integer)((List)var22.get(var13)).get(0);
                                    var27 = Math.max(var30, var31);
                                 }
                              }

                              var29 = bpb.pC;
                              if (this.UT != null && this.UT.pC(var8)) {
                                 var28 = bpb.pC;
                                 var29 = bpb.A;
                              }
                           }

                           if (var28 == bpb.pC) {
                              if (this.ld[0][var38 - 1] != 0) {
                                 var28 = var29;
                              }
                           } else if (var28 == bpb.A) {
                              if (this.ld[0][var13 - 1] != 0) {
                                 var28 = var29;
                              }
                           } else if (var28 == bpb.kS && (this.ld[0][var38 - 1] != 0 || this.ld[0][var13 - 1] != 0)) {
                              var28 = var29;
                           }

                           if (var28 != null) {
                              if (var28 == bpb.pC) {
                                 var47 = (Integer)((List)var22.get(var38)).get(0);
                              } else if (var28 == bpb.A) {
                                 var47 = (Integer)((List)var22.get(var13)).get(0);
                              }

                              byte var48 = 1;
                              byte var49 = 1;
                              if (this.ld[0][var38 - 1] <= 0 && var28 == bpb.A) {
                                 int var32 = Math.max(var47, var27);
                                 if (var32 > 0) {
                                    boolean var33 = false;

                                    for (int var35 : this.E.wS(var38)) {
                                       if (var35 >= var32) {
                                          var33 = true;
                                          break;
                                       }
                                    }

                                    if (!var33) {
                                       var48 = -1;
                                    }
                                 }
                              }

                              this.ld[0][var38 - 1] = var48;
                              this.ld[0][var13 - 1] = var49;
                              this.gp.put(var38, new bpa(var28, var38, var13, var47 <= 0 ? -1 : var47));
                           }
                        }
                     }
                  }
               } catch (Exception var36) {
                  com.pnfsoftware.jeb.corei.parsers.dexdec.rQ.pC(var36, this.sY);
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

         for (bot.Av var3 : this.ys.pC) {
            bou var4 = var3.pC;

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

                        int var24 = this.E.wS(var23).size();
                        int var15 = this.E.wS(var12).size();
                        if (var24 <= 0 || var15 <= 0) {
                           throw new RuntimeException();
                        }

                        if (var24 <= 2 || var15 <= 2) {
                           boolean var16 = false;
                           if (var24 >= 2) {
                              for (int var18 : this.E.wS(var23)) {
                                 if (!var13.contains(var18)) {
                                    var16 = true;
                                    break;
                                 }
                              }
                           }

                           bpb var26 = null;
                           bpb var25;
                           if (var24 == 1 && var15 == 1) {
                              var25 = bpb.kS;
                           } else if (var24 == 1 && var15 >= 2) {
                              var25 = bpb.A;
                           } else if (var24 >= 2 && var15 == 1) {
                              var25 = var16 ? bpb.pC : bpb.kS;
                           } else if (!var16) {
                              var25 = bpb.A;
                           } else {
                              var25 = bpb.A;
                              var26 = bpb.pC;
                              if (this.UT != null && this.UT.pC(var7)) {
                                 var25 = bpb.pC;
                                 var26 = bpb.A;
                              }
                           }

                           if (var25 == bpb.pC) {
                              if (this.ld[0][var23 - 1] != 0) {
                                 var25 = var26;
                              }
                           } else if (var25 == bpb.A) {
                              if (this.ld[0][var12 - 1] != 0) {
                                 var25 = var26;
                              }
                           } else if (var25 == bpb.kS && (this.ld[0][var23 - 1] != 0 || this.ld[0][var12 - 1] != 0)) {
                              var25 = var26;
                           }

                           if (var25 != null) {
                              int var19 = -1;
                              if (var25 == bpb.A) {
                                 List var20 = this.E.wS(var12);
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
                                    var25 = bpb.kS;
                                 }
                              } else if (var25 == bpb.pC) {
                                 List var27 = this.E.wS(var23);
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
                                    var25 = bpb.kS;
                                 }
                              } else {
                                 var19 = -1;
                              }

                              if (this.ld[0][var23 - 1] <= 0 && var25 == bpb.A) {
                                 this.ld[0][var23 - 1] = -1;
                              } else {
                                 this.ld[0][var23 - 1] = 1;
                              }

                              this.ld[0][var12 - 1] = 1;
                              if (var25 == bpb.pC && var19 > var12) {
                                 var12 = var19 - 1;
                              }

                              this.gp.put(var23, new bpa(var25, var23, var12, var19 <= 0 ? -1 : var19));
                           }
                        }
                     }
                  }
               } catch (Exception var21) {
                  com.pnfsoftware.jeb.corei.parsers.dexdec.rQ.pC(var21, this.sY);
               }
            }

            var1++;
         }
      }
   }

   private void pC(bot var1, int var2, int[] var3) {
      int var4 = var3[0];
      int var5 = var3[1];

      while (var2 >= 1) {
         bot.Av var6 = (bot.Av)var1.pC.get(--var2);
         bou var7 = var6.pC;
         var4 = (Integer)((List)var6.A.pC.get(var4 - 1)).get(0);
         int var8 = 0;

         for (int var10 : (List)var6.A.pC.get(var5 - 1)) {
            if (var7.wS(var10).contains(var4) && var10 > var8) {
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

         for (int var3 = this.E.A(); var3 >= 1; var3--) {
            if (this.E.wS(var3).size() == 2 && this.ld[0][var3 - 1] <= 0) {
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
                  this.oT.put(var3, new bor(bos.pC, var3, var4));

                  for (int var9 : var2) {
                     if (var9 < var4 && this.E.pC(var3, var9)) {
                        this.ld[0][var9 - 1] = 1;
                        this.oT.put(var9, new bor(bos.pC, var9, var4));
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

         for (int var3 = this.E.A(); var3 >= 1; var3--) {
            int var4 = this.E.wS(var3).size();
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
                  this.fI.put(var3, new bor(bos.A, var3, var5));

                  for (int var10 : var2) {
                     if (var10 < var5 && this.E.pC(var3, var10)) {
                        this.ld[0][var10 - 1] = 1;
                        this.fI.put(var10, new bor(bos.A, var10, var5));
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
         int var4 = this.E.pC().size();
         List var3;
         if (var4 <= 1) {
            var3 = this.E.pC(false, true, 0);
         } else {
            var3 = this.E.kS(false);
         }

         HashMap var18 = new HashMap();
         if (var1) {
            this.fI = var18;
         } else {
            this.oT = var18;
         }

         long var5 = 0L;

         for (int var7 = this.E.A(); var7 >= 1; var7--) {
            List var8 = this.E.wS(var7);
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
                     com.pnfsoftware.jeb.corei.parsers.dexdec.rQ.pC(new RuntimeException("structureC abort"), this.sY);
                     break;
                  }

                  long var20 = System.currentTimeMillis();
                  var11 = (Set)this.E.pC(false, true, var7).get(var7 - 1);
                  var5 += System.currentTimeMillis() - var20;

                  for (Integer var16 : var11) {
                     if (var16 >= var10 && (var12 == null || var16 < var12)) {
                        var12 = var16;
                     }
                  }
               }

               if (var12 != null && var12 > var7) {
                  boolean var21 = true;
                  Set var23 = this.E.A(var7, var12);
                  bou var24 = new bou(this.E);
                  var24.sY(var7);
                  if (var7 != 1) {
                     Set var25 = var24.E(1);
                     var21 = Collections.disjoint(var25, var23);
                  }

                  if (var21) {
                     Set var26 = var24.E(var12);
                     var21 = Collections.disjoint(var26, var23);
                     if (var21) {
                        this.ld[0][var7 - 1] = 1;
                        bos var17 = var1 ? bos.A : bos.pC;
                        var18.put(var7, new bor(var17, var7, var12));
                     }
                  }
               }
            }
         }
      }
   }
}
