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

public class bth {
   private static final ILogger Dw = GlobalLog.getLogger(bth.class);
   public static final int q = 3;
   private int Uv = 3;
   private bsz oW;
   private bsv gO;
   private String nf;
   private bsu gP;
   private int[][] za;
   private MultiMap lm;
   private Map zz;
   private Map JY;
   public boolean RF = false;
   public static int xK = 10;

   public bth(bsv var1, String var2, byte[] var3) {
      this.gO = var1;
      this.nf = var2;
      this.gP = this.gO.zz();
      this.za = new int[this.gP.q.size()][];
      int var4 = 0;

      for (bsu.eo var6 : this.gP.q) {
         int var7 = var6.q.gO();
         this.za[var4] = new int[var7];

         for (int var8 = 0; var8 < var7; var8++) {
            this.za[var4][var8] = 0;
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

      var10 += var1.gO() * var1.nf() % 10;
      var10 &= 255;
      var10 %= 10;
      if (var10 >= xK) {
         this.lm = new MultiMap();
         this.zz = new HashMap();
         this.JY = new HashMap();
         this.RF = true;
      }
   }

   public bth(bsv var1) {
      this(var1, null, null);
   }

   public void q(int var1) {
      if (var1 <= 0 || var1 > 3) {
         var1 = 3;
      }

      this.Uv = var1;
   }

   public int q() {
      return this.Uv;
   }

   public void q(bsz var1) {
      this.oW = var1;
   }

   public bsz RF() {
      return this.oW;
   }

   public btj xK() {
      this.gO();
      this.Uv();
      this.oW();
      return new btj(this.lm, this.zz, this.JY);
   }

   void Dw() {
      if (this.lm == null) {
         this.lm = new MultiMap();
         List var1 = this.gO.LK();
         int var2 = 0;

         for (bsu.eo var4 : this.gP.q) {
            bsv var5 = var4.q;

            for (List var7 : var4.RF.q) {
               try {
                  int var8 = (Integer)var7.get(0);
                  int var9 = 0;
                  ArrayList var10 = new ArrayList();

                  for (int var12 : var5.xK(var8)) {
                     if (var7.contains(var12)) {
                        var10.add(var12);
                        if (var12 > var9) {
                           var9 = var12;
                        }
                     }
                  }

                  if (var9 > 0) {
                     int[] var37 = new int[]{var8, var9};
                     this.q(this.gP, var2, var37);
                     int var38 = var37[0];
                     int var13 = var37[1];
                     if (var13 < var38) {
                        throw new RuntimeException("latch located before header");
                     }

                     if (var38 > 0 && var13 > 0 && (this.za[0][var38 - 1] == 0 || this.za[0][var13 - 1] == 0)) {
                        ArrayList var14 = new ArrayList();

                        for (int var15 = var38; var15 <= var13; var15++) {
                           var14.add(var15);
                        }

                        boolean var39 = true;
                        int var16 = this.gO.oW(var38).size();
                        int var17 = this.gO.oW(var13).size();
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
                              for (int var26 : this.gO.oW(var24)) {
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
                           btd var29 = null;
                           btd var28;
                           if (!var18 && !var19) {
                              var28 = btd.xK;
                           } else if (!var18 && var19) {
                              var28 = btd.RF;
                           } else if (var18 && !var19) {
                              var28 = btd.q;
                           } else {
                              if (((List)var22.get(var38)).equals(var22.get(var13))) {
                                 var28 = btd.RF;
                              } else {
                                 var28 = btd.RF;
                                 if (var42 != null) {
                                    var27 = var42;
                                 } else {
                                    int var30 = (Integer)((List)var22.get(var38)).get(0);
                                    int var31 = (Integer)((List)var22.get(var13)).get(0);
                                    var27 = Math.max(var30, var31);
                                 }
                              }

                              var29 = btd.q;
                              if (this.oW != null && this.oW.q(var8)) {
                                 var28 = btd.q;
                                 var29 = btd.RF;
                              }
                           }

                           if (var28 == btd.q) {
                              if (this.za[0][var38 - 1] != 0) {
                                 var28 = var29;
                              }
                           } else if (var28 == btd.RF) {
                              if (this.za[0][var13 - 1] != 0) {
                                 var28 = var29;
                              }
                           } else if (var28 == btd.xK && (this.za[0][var38 - 1] != 0 || this.za[0][var13 - 1] != 0)) {
                              var28 = var29;
                           }

                           if (var28 != null) {
                              if (var28 == btd.q) {
                                 var47 = (Integer)((List)var22.get(var38)).get(0);
                              } else if (var28 == btd.RF) {
                                 var47 = (Integer)((List)var22.get(var13)).get(0);
                              }

                              byte var48 = 1;
                              byte var49 = 1;
                              if (this.za[0][var38 - 1] <= 0 && var28 == btd.RF) {
                                 int var32 = Math.max(var47, var27);
                                 if (var32 > 0) {
                                    boolean var33 = false;

                                    for (int var35 : this.gO.oW(var38)) {
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

                              this.za[0][var38 - 1] = var48;
                              this.za[0][var13 - 1] = var49;
                              this.lm.put(var38, new btc(var28, var38, var13, var47 <= 0 ? -1 : var47));
                           }
                        }
                     }
                  }
               } catch (Exception var36) {
                  com.pnfsoftware.jeb.corei.parsers.dexdec.tw.q(var36, this.nf);
               }
            }

            var2++;
         }
      }
   }

   void Uv() {
      if (this.q() >= 3) {
         this.Dw();
      } else if (this.lm == null) {
         this.lm = new MultiMap();
         int var1 = 0;

         for (bsu.eo var3 : this.gP.q) {
            bsv var4 = var3.q;

            for (List var6 : var3.RF.q) {
               try {
                  int var7 = (Integer)var6.get(0);
                  int var8 = 0;
                  ArrayList var9 = new ArrayList();

                  for (int var11 : var4.xK(var7)) {
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
                     this.q(this.gP, var1, var22);
                     int var23 = var22[0];
                     int var12 = var22[1];
                     if (var23 > 0 && var12 > 0 && (this.za[0][var23 - 1] == 0 || this.za[0][var12 - 1] == 0)) {
                        ArrayList var13 = new ArrayList();

                        for (int var14 = var23; var14 <= var12; var14++) {
                           var13.add(var14);
                        }

                        int var24 = this.gO.oW(var23).size();
                        int var15 = this.gO.oW(var12).size();
                        if (var24 <= 0 || var15 <= 0) {
                           throw new RuntimeException();
                        }

                        if (var24 <= 2 || var15 <= 2) {
                           boolean var16 = false;
                           if (var24 >= 2) {
                              for (int var18 : this.gO.oW(var23)) {
                                 if (!var13.contains(var18)) {
                                    var16 = true;
                                    break;
                                 }
                              }
                           }

                           btd var26 = null;
                           btd var25;
                           if (var24 == 1 && var15 == 1) {
                              var25 = btd.xK;
                           } else if (var24 == 1 && var15 >= 2) {
                              var25 = btd.RF;
                           } else if (var24 >= 2 && var15 == 1) {
                              var25 = var16 ? btd.q : btd.xK;
                           } else if (!var16) {
                              var25 = btd.RF;
                           } else {
                              var25 = btd.RF;
                              var26 = btd.q;
                              if (this.oW != null && this.oW.q(var7)) {
                                 var25 = btd.q;
                                 var26 = btd.RF;
                              }
                           }

                           if (var25 == btd.q) {
                              if (this.za[0][var23 - 1] != 0) {
                                 var25 = var26;
                              }
                           } else if (var25 == btd.RF) {
                              if (this.za[0][var12 - 1] != 0) {
                                 var25 = var26;
                              }
                           } else if (var25 == btd.xK && (this.za[0][var23 - 1] != 0 || this.za[0][var12 - 1] != 0)) {
                              var25 = var26;
                           }

                           if (var25 != null) {
                              int var19 = -1;
                              if (var25 == btd.RF) {
                                 List var20 = this.gO.oW(var12);
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
                                    var25 = btd.xK;
                                 }
                              } else if (var25 == btd.q) {
                                 List var27 = this.gO.oW(var23);
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
                                    var25 = btd.xK;
                                 }
                              } else {
                                 var19 = -1;
                              }

                              if (this.za[0][var23 - 1] <= 0 && var25 == btd.RF) {
                                 this.za[0][var23 - 1] = -1;
                              } else {
                                 this.za[0][var23 - 1] = 1;
                              }

                              this.za[0][var12 - 1] = 1;
                              if (var25 == btd.q && var19 > var12) {
                                 var12 = var19 - 1;
                              }

                              this.lm.put(var23, new btc(var25, var23, var12, var19 <= 0 ? -1 : var19));
                           }
                        }
                     }
                  }
               } catch (Exception var21) {
                  com.pnfsoftware.jeb.corei.parsers.dexdec.tw.q(var21, this.nf);
               }
            }

            var1++;
         }
      }
   }

   private void q(bsu var1, int var2, int[] var3) {
      int var4 = var3[0];
      int var5 = var3[1];

      while (var2 >= 1) {
         bsu.eo var6 = (bsu.eo)var1.q.get(--var2);
         bsv var7 = var6.q;
         var4 = (Integer)((List)var6.RF.q.get(var4 - 1)).get(0);
         int var8 = 0;

         for (int var10 : (List)var6.RF.q.get(var5 - 1)) {
            if (var7.oW(var10).contains(var4) && var10 > var8) {
               var8 = var10;
            }
         }

         var5 = var8;
      }

      var3[0] = var4;
      var3[1] = var5;
   }

   void oW() {
      if (this.q() >= 2) {
         this.nf();
      } else if (this.zz == null) {
         this.zz = new HashMap();
         List var1 = this.gO.xK(false);
         HashSet var2 = new HashSet();

         for (int var3 = this.gO.gO(); var3 >= 1; var3--) {
            if (this.gO.oW(var3).size() == 2 && this.za[0][var3 - 1] <= 0) {
               int var4 = 0;
               int var5 = 1;

               for (int var7 : var1) {
                  if (var7 == var3 && this.gO.xK(var5).size() >= 2 && var5 > var4) {
                     var4 = var5;
                  }

                  var5++;
               }

               if (var4 <= var3) {
                  var2.add(var3);
               } else {
                  this.za[0][var3 - 1] = 1;
                  this.zz.put(var3, new bss(bst.q, var3, var4));

                  for (int var9 : var2) {
                     if (var9 < var4 && this.gO.q(var3, var9)) {
                        this.za[0][var9 - 1] = 1;
                        this.zz.put(var9, new bss(bst.q, var9, var4));
                     }
                  }

                  var2.clear();
               }
            }
         }
      }
   }

   void gO() {
      if (this.q() >= 2) {
         this.gP();
      } else if (this.JY == null) {
         this.JY = new HashMap();
         List var1 = this.gO.xK(false);
         HashSet var2 = new HashSet();

         for (int var3 = this.gO.gO(); var3 >= 1; var3--) {
            int var4 = this.gO.oW(var3).size();
            if (var4 >= 3 && this.za[0][var3 - 1] <= 0) {
               int var5 = 0;
               int var6 = 1;

               for (int var8 : var1) {
                  if (var8 == var3 && this.gO.xK(var6).size() >= var4 && var6 > var5) {
                     var5 = var6;
                  }

                  var6++;
               }

               if (var5 <= var3) {
                  var2.add(var3);
               } else {
                  this.za[0][var3 - 1] = 1;
                  this.JY.put(var3, new bss(bst.RF, var3, var5));

                  for (int var10 : var2) {
                     if (var10 < var5 && this.gO.q(var3, var10)) {
                        this.za[0][var10 - 1] = 1;
                        this.JY.put(var10, new bss(bst.RF, var10, var5));
                     }
                  }

                  var2.clear();
               }
            }
         }
      }
   }

   private void nf() {
      this.q(false);
   }

   private void gP() {
      this.q(true);
   }

   private void q(boolean var1) {
      Map var2 = var1 ? this.JY : this.zz;
      if (var2 == null) {
         int var4 = this.gO.oW().size();
         List var3;
         if (var4 <= 1) {
            var3 = this.gO.q(false, true, 0);
         } else {
            var3 = this.gO.Dw(false);
         }

         HashMap var18 = new HashMap();
         if (var1) {
            this.JY = var18;
         } else {
            this.zz = var18;
         }

         long var5 = 0L;

         for (int var7 = this.gO.gO(); var7 >= 1; var7--) {
            List var8 = this.gO.oW(var7);
            int var9 = var8.size();
            if ((var1 && var9 > 2 || !var1 && var9 == 2) && this.za[0][var7 - 1] <= 0) {
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
                     com.pnfsoftware.jeb.corei.parsers.dexdec.tw.q(new RuntimeException("structureC abort"), this.nf);
                     break;
                  }

                  long var20 = System.currentTimeMillis();
                  var11 = (Set)this.gO.q(false, true, var7).get(var7 - 1);
                  var5 += System.currentTimeMillis() - var20;

                  for (Integer var16 : var11) {
                     if (var16 >= var10 && (var12 == null || var16 < var12)) {
                        var12 = var16;
                     }
                  }
               }

               if (var12 != null && var12 > var7) {
                  boolean var21 = true;
                  Set var23 = this.gO.RF(var7, var12);
                  bsv var24 = new bsv(this.gO);
                  var24.za(var7);
                  if (var7 != 1) {
                     Set var25 = var24.gP(1);
                     var21 = Collections.disjoint(var25, var23);
                  }

                  if (var21) {
                     Set var26 = var24.gP(var12);
                     var21 = Collections.disjoint(var26, var23);
                     if (var21) {
                        this.za[0][var7 - 1] = 1;
                        bst var17 = var1 ? bst.RF : bst.q;
                        var18.put(var7, new bss(var17, var7, var12));
                     }
                  }
               }
            }
         }
      }
   }

   private void RF(boolean var1) {
      Map var2 = var1 ? this.JY : this.zz;
      if (var2 == null) {
         List var3 = this.gO.Dw(false);
         HashMap var12 = new HashMap();
         if (var1) {
            this.JY = var12;
         } else {
            this.zz = var12;
         }

         for (int var4 = this.gO.gO(); var4 >= 1; var4--) {
            List var5 = this.gO.oW(var4);
            int var6 = var5.size();
            if ((var1 && var6 > 2 || !var1 && var6 == 2) && this.za[0][var4 - 1] <= 0) {
               int var7 = Integers.min(var5);
               Set var8 = (Set)var3.get(var4 - 1);
               Integer var9 = null;

               for (Integer var11 : var8) {
                  if (var11 >= var7 && (var9 == null || var11 < var9)) {
                     var9 = var11;
                  }
               }

               this.za[0][var4 - 1] = 1;
               bst var13 = var1 ? bst.RF : bst.q;
               var12.put(var4, new bss(var13, var4, var9));
            }
         }
      }
   }
}
