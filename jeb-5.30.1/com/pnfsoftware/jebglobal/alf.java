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

public class alf {
   private static final StructuredLogger Dw = aeg.q(alf.class);
   public static final int q = 3;
   private int Uv = 3;
   private akz oW;
   private akv gO;
   private IERoutineContext nf;
   private aku gP;
   private int[][] za;
   private MultiMap lm;
   private Map zz;
   private Map JY;
   public boolean RF = false;
   public static int xK = 10;

   public alf(akv var1, IERoutineContext var2, byte[] var3, INativeDecompilerUnit var4) {
      this.gO = var1;
      this.nf = var2;
      if (var4 != null) {
         String var5 = var4.getFormatType();
         if (var5.contains(cvm.q(new byte[]{47, 6}, 2, 241)) && var5.contains(cvm.q(new byte[]{-54, 23, 27}, 1, 171))) {
            xK = 10;
         }
      }

      this.gP = this.gO.zz();
      this.za = new int[this.gP.q.size()][];
      int var11 = 0;

      for (aku.eo var7 : this.gP.q) {
         int var8 = var7.q.gO();
         this.za[var11] = new int[var8];

         for (int var9 = 0; var9 < var8; var9++) {
            this.za[var11][var9] = 0;
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

      var12 += var1.gO() * var1.nf() % 10;
      var12 &= 255;
      var12 %= 10;
      if (var12 >= xK) {
         this.lm = new MultiMap();
         this.zz = new HashMap();
         this.JY = new HashMap();
         this.RF = true;
      }
   }

   public alf(akv var1) {
      this(var1, null, null, null);
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

   public void q(akz var1) {
      this.oW = var1;
   }

   public akz RF() {
      return this.oW;
   }

   public alg xK() {
      this.gO();
      this.Uv();
      this.oW();
      return new alg(this.JY, this.lm, this.zz);
   }

   void Dw() {
      if (this.lm == null) {
         this.lm = new MultiMap();
         List var1 = this.gO.LK();
         int var2 = 0;

         for (aku.eo var4 : this.gP.q) {
            akv var5 = var4.q;

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
                     int[] var36 = new int[]{var8, var9};
                     this.q(this.gP, var2, var36);
                     int var37 = var36[0];
                     int var13 = var36[1];
                     if (var13 < var37) {
                        throw new RuntimeException("latch located before header");
                     }

                     if (var37 > 0 && var13 > 0 && (this.za[0][var37 - 1] == 0 || this.za[0][var13 - 1] == 0)) {
                        ArrayList var14 = new ArrayList();

                        for (int var15 = var37; var15 <= var13; var15++) {
                           var14.add(var15);
                        }

                        int var38 = this.gO.oW(var37).size();
                        int var16 = this.gO.oW(var13).size();
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
                              for (int var25 : this.gO.oW(var23)) {
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
                           alc var28 = null;
                           alc var27;
                           if (!var17 && !var18) {
                              var27 = alc.xK;
                           } else if (!var17 && var18) {
                              var27 = alc.RF;
                           } else if (var17 && !var18) {
                              var27 = alc.q;
                           } else {
                              if (((List)var21.get(var37)).equals(var21.get(var13))) {
                                 var27 = alc.RF;
                              } else {
                                 var27 = alc.RF;
                                 if (var41 != null) {
                                    var26 = var41;
                                 } else {
                                    int var29 = (Integer)((List)var21.get(var37)).get(0);
                                    int var30 = (Integer)((List)var21.get(var13)).get(0);
                                    var26 = Math.max(var29, var30);
                                 }
                              }

                              var28 = alc.q;
                              if (this.oW != null && this.oW.q(var8)) {
                                 var27 = alc.q;
                                 var28 = alc.RF;
                              }
                           }

                           if (var27 == alc.q) {
                              if (this.za[0][var37 - 1] != 0) {
                                 var27 = var28;
                              }
                           } else if (var27 == alc.RF) {
                              if (this.za[0][var13 - 1] != 0) {
                                 var27 = var28;
                              }
                           } else if (var27 == alc.xK && (this.za[0][var37 - 1] != 0 || this.za[0][var13 - 1] != 0)) {
                              var27 = var28;
                           }

                           if (var27 != null) {
                              if (var27 == alc.q) {
                                 var46 = (Integer)((List)var21.get(var37)).get(0);
                              } else if (var27 == alc.RF) {
                                 var46 = (Integer)((List)var21.get(var13)).get(0);
                              }

                              byte var47 = 1;
                              byte var48 = 1;
                              if (this.za[0][var37 - 1] <= 0 && var27 == alc.RF) {
                                 int var31 = Math.max(var46, var26);
                                 if (var31 > 0) {
                                    boolean var32 = false;

                                    for (int var34 : this.gO.oW(var37)) {
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

                              this.za[0][var37 - 1] = var47;
                              this.za[0][var13 - 1] = var48;
                              this.lm.put(var37, new alb(var37, var13, var46, var27, var26));
                           }
                        }
                     }
                  }
               } catch (Exception var35) {
                  aeb.q(var35, this.nf);
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

         for (aku.eo var3 : this.gP.q) {
            akv var4 = var3.q;

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

                           alc var26 = null;
                           alc var25;
                           if (var24 == 1 && var15 == 1) {
                              var25 = alc.xK;
                           } else if (var24 == 1 && var15 >= 2) {
                              var25 = alc.RF;
                           } else if (var24 >= 2 && var15 == 1) {
                              var25 = var16 ? alc.q : alc.xK;
                           } else if (!var16) {
                              var25 = alc.RF;
                           } else {
                              var25 = alc.RF;
                              var26 = alc.q;
                              if (this.oW != null && this.oW.q(var7)) {
                                 var25 = alc.q;
                                 var26 = alc.RF;
                              }
                           }

                           if (var25 == alc.q) {
                              if (this.za[0][var23 - 1] != 0) {
                                 var25 = var26;
                              }
                           } else if (var25 == alc.RF) {
                              if (this.za[0][var12 - 1] != 0) {
                                 var25 = var26;
                              }
                           } else if (var25 == alc.xK && (this.za[0][var23 - 1] != 0 || this.za[0][var12 - 1] != 0)) {
                              var25 = var26;
                           }

                           if (var25 != null) {
                              int var19 = 0;
                              if (var25 == alc.RF) {
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
                                    var25 = alc.xK;
                                 }
                              } else if (var25 == alc.q) {
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
                                    var25 = alc.xK;
                                 }
                              } else {
                                 var19 = 0;
                              }

                              if (this.za[0][var23 - 1] <= 0 && var25 == alc.RF) {
                                 this.za[0][var23 - 1] = -1;
                              } else {
                                 this.za[0][var23 - 1] = 1;
                              }

                              this.za[0][var12 - 1] = 1;
                              if (var25 == alc.q && var19 > var12) {
                                 var12 = var19 - 1;
                              }

                              this.lm.put(var23, new alb(var23, var12, var19, var25));
                           }
                        }
                     }
                  }
               } catch (Exception var21) {
                  aeb.q(var21, this.nf);
               }
            }

            var1++;
         }
      }
   }

   private void q(aku var1, int var2, int[] var3) {
      int var4 = var3[0];
      int var5 = var3[1];

      while (var2 >= 1) {
         aku.eo var6 = (aku.eo)var1.q.get(--var2);
         akv var7 = var6.q;
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
                  this.zz.put(var3, new akt(var3, var4));

                  for (int var9 : var2) {
                     if (var9 < var4 && this.gO.q(var3, var9)) {
                        this.za[0][var9 - 1] = 1;
                        this.zz.put(var9, new akt(var9, var4));
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
                  this.JY.put(var3, new akt(var3, var5));

                  for (int var10 : var2) {
                     if (var10 < var5 && this.gO.q(var3, var10)) {
                        this.za[0][var10 - 1] = 1;
                        this.JY.put(var10, new akt(var10, var5));
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

         HashMap var17 = new HashMap();
         if (var1) {
            this.JY = var17;
         } else {
            this.zz = var17;
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
                     aeb.q(new RuntimeException("structureC abort"), this.nf);
                     break;
                  }

                  long var19 = System.currentTimeMillis();
                  var11 = (Set)this.gO.q(false, true, var7).get(var7 - 1);
                  var5 += System.currentTimeMillis() - var19;

                  for (Integer var16 : var11) {
                     if (var16 >= var10 && (var12 == null || var16 < var12)) {
                        var12 = var16;
                     }
                  }
               }

               if (var12 != null && var12 > var7) {
                  boolean var20 = true;
                  Set var22 = this.gO.RF(var7, var12);
                  akv var23 = new akv(this.gO);
                  var23.za(var7);
                  if (var7 != 1) {
                     Set var24 = var23.gP(1);
                     var20 = Collections.disjoint(var24, var22);
                  }

                  if (var20) {
                     Set var25 = var23.gP(var12);
                     var20 = Collections.disjoint(var25, var22);
                     if (var20) {
                        this.za[0][var7 - 1] = 1;
                        var17.put(var7, new akt(var7, var12));
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
               var12.put(var4, new akt(var4, var9));
            }
         }
      }
   }
}
