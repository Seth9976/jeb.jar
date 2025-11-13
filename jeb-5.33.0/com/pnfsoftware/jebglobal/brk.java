package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexItem;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DOptimizerType;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMasterOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMasterOptimizerInstrumenter;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOptimizer;
import com.pnfsoftware.jeb.util.collect.CollectionUtil;
import com.pnfsoftware.jeb.util.collect.Maps;
import com.pnfsoftware.jeb.util.concurrent.Watchdog;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class brk implements IDMasterOptimizer {
   private static final ILogger pC = GlobalLog.getLogger(brk.class);
   private static volatile boolean A;
   private IDMethodContext kS;
   private List wS;
   private Map UT;
   private int E = -1;
   private boolean sY = false;
   private int ys = 0;
   private int ld = 0;
   private int gp = 0;
   private int oT = 0;
   private int fI = 0;
   private int WR = 0;
   private int NS = 0;
   private List vP = new ArrayList();
   private List xC = new ArrayList();
   private List ED = new ArrayList();
   private List Sc = new ArrayList();
   private Set ah = null;
   private Set eP = new HashSet();
   private List UO = new ArrayList();
   private long Ab;

   public brk(IDMethodContext var1, boolean var2) {
      this.kS = var1;
      if (var2) {
         this.ld();
      }
   }

   public brk(List var1, boolean var2) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.wS = new ArrayList(var1);
         if (var2) {
            this.gp();
         }
      }
   }

   @Override
   public IDMethodContext getTarget() {
      return this.kS;
   }

   @Override
   public boolean add(IDOptimizer var1) {
      return this.pC(var1, true);
   }

   public boolean pC(IDOptimizer var1, boolean var2) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else if (this.vP.contains(var1)) {
         return false;
      } else {
         if (var2) {
            this.ED.add(var1);
         } else {
            this.xC.add(var1);
         }

         this.vP.add(var1);
         return true;
      }
   }

   @Override
   public boolean remove(IDOptimizer var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         if (this.vP.contains(var1)) {
            if (this.xC.remove(var1)) {
               this.vP.remove(var1);
               return true;
            }

            if (this.ED.remove(var1)) {
               this.vP.remove(var1);
               return true;
            }
         }

         return false;
      }
   }

   @Override
   public List getListOfOptimizers() {
      return Collections.unmodifiableList(this.vP);
   }

   private void ld() {
      this.vP.add(new bzu());
      this.vP.add(new bzt());
      this.vP.add(new bzy());
      this.vP.add(new cao());
      this.vP.add(new cap());
      this.vP.add(new bzv());
      this.vP.add(new byl());
      this.vP.add(new ccl());
      this.vP.add(new ccj());
      this.vP.add(new cck());
      this.vP.add(new bzz());
      this.vP.add(new bxe());
      this.vP.add(new bxg());
      this.vP.add(new bxf());
      this.vP.add(new bxa());
      this.vP.add(new cay());
      this.vP.add(new cae());
      this.vP.add(new bxb());
      this.vP.add(new bws());
      this.vP.add(new cbk());
      this.vP.add(new cbe());
      this.vP.add(new byh());
      this.vP.add(new cbm());
      this.vP.add(new caq());
      this.vP.add(new bxk());
      this.vP.add(new bxl());
      this.vP.add(new can());
      this.vP.add(new byo());
      this.vP.add(new byp());
      this.vP.add(new byr());
      this.vP.add(new byk());
      this.vP.add(new bye());
      this.vP.add(new caz());
      this.vP.add(new cax());
      this.vP.add(new cbc());
      this.vP.add(new byi());
      this.vP.add(new bwz());
      this.vP.add(new cbn());
      this.vP.add(new cbz());
      this.vP.add(new bym());
      this.vP.add(new byc());
      this.vP.add(new ccd());
      this.vP.add(new cch());
      this.vP.add(new cbo());
      this.vP.add(new cbp());
      this.vP.add(new cbx());
      this.vP.add(new cby());
      this.vP.add(new ccm());
      this.vP.add(new byf());
      this.vP.add(new ccb());
      this.vP.add(new cbh());
      this.vP.add(new cbf());
      this.vP.add(new cav());
      this.vP.add(new byd());
      this.vP.add(new byn());
      this.vP.add(new cab());
      this.vP.add(new bxo());
      this.vP.add(new bxn());
      this.vP.add(new cah());
      this.vP.add(new cag());
      this.vP.add(new car());
      this.vP.add(new cca());
      this.vP.add(new bzl());
      this.vP.add(new bzj());
      this.vP.add(new bxy());
      this.vP.add(new bza());
      this.vP.add(new bxp(this.A(), this.kS()));
      this.vP.add(new ccp());
      this.vP.add(new ccq());
      this.vP.add(new bxh());
      this.vP.add(new cal());
      this.vP.add(new cam());
      this.vP.add(new cas());
      this.vP.add(new bzg());
      this.vP.add(new bzf());
      this.vP.add(new cau());
      this.vP.add(new bxj());
      this.vP.add(new ccn());
      this.vP.add(new bwr());
      this.vP.add(new cbd());
      this.vP.add(new bwu());
      this.vP.add(new bwt());
      this.vP.add(new byg());
      this.vP.add(new bya());
      this.vP.add(new bxc());
      this.vP.add(new ccs(true, false, true));
      this.vP.add(new bwq());
      this.vP.add(new ccr());
      this.vP.add(new bwv(this.UT()));
      this.vP.add(new bww(this.E(), bww.Av.kS));
      this.vP.add(new bxi());
      this.vP.add(new bxu());
      this.vP.add(new bxv());
      this.vP.add(new bxt());
      this.vP.add(new byq());
      this.vP.add(new bze());
      this.vP.add(new bxd());
      this.vP.add(new bzr());
      this.vP.add(new bzs());
      this.vP.add(new bzp(this.sY()));
      this.vP.add(new bzn(this.ys()));
      this.vP.add(new bzi(this.wS()));
      this.vP.add(new byx());
      this.vP.add(new byy());
      this.vP.add(new bzd());
      this.vP.add(new bxw());
      this.vP.add(new bwo());
      this.vP.add(new bwp());
      this.vP.add(new cbl());
      this.vP.add(new cbz());
      this.vP.add(new cbi());
      this.vP.add(new ccg());
      this.vP.add(new cbj());
      this.vP.add(new byt());
      this.vP.add(new byv());
      this.vP.add(new bxm());
      this.vP.add(new cco());
   }

   private void gp() {
      this.vP.add(new bwn());
   }

   @Override
   public IDOptimizer findOptimizer(Class var1) {
      for (IDOptimizer var3 : this.vP) {
         if (var1.isInstance(var3)) {
            return var3;
         }
      }

      return null;
   }

   @Override
   public void setOptimizerEnabled(IDOptimizer var1, boolean var2) {
      if (var1 != null) {
         if (var1 instanceof AbstractDOptimizer) {
            ((AbstractDOptimizer)var1).setEnabled(var2);
         } else if (var2) {
            this.Sc.remove(var1);
         } else {
            this.Sc.add(var1);
         }
      }
   }

   @Override
   public void registerInstrumenter(IDMasterOptimizerInstrumenter var1) {
      if (var1 != null) {
         this.UO.add(var1);
      }
   }

   @Override
   public void unregisterInstrumenter(IDMasterOptimizerInstrumenter var1) {
      if (var1 != null) {
         this.UO.remove(var1);
      }
   }

   @Override
   public List getInstrumenters() {
      return Collections.unmodifiableList(this.UO);
   }

   public Map pC() {
      return this.UT;
   }

   @Override
   public int perform() {
      this.Ab = 0L;
      Object[] var10000 = new Object[0];
      long var1 = System.currentTimeMillis();

      int var3;
      try {
         var3 = this.oT();
      } finally {
         long var7 = System.currentTimeMillis() - var1;
         var10000 = new Object[]{var7, com.pnfsoftware.jeb.corei.parsers.dexdec.rQ.pC(this.Ab)};
      }

      return var3;
   }

   private int oT() {
      this.vP.sort(new brl(this));
      this.UT = new HashMap();
      boolean var1 = this.kS != null;
      boolean var2 = this.E < 0;
      int var3 = var2 ? 5000 : 100;
      int var4 = 2 * var3;
      int var5 = -1;
      int var6 = -1;
      int var7 = 5;
      LinkedHashSet var8 = new LinkedHashSet();
      Object var9 = null;
      int var10 = 0;
      int var11 = 1;
      int var12 = 0;

      while (this.E < 0 || var11 <= this.E) {
         if (var11 > var3) {
            boolean var13 = false;
            if (var1 && var11 <= var4 && var5 <= var6) {
               var13 = true;
               if (var5 < var6) {
                  var7 = 5;
               } else if (--var7 < 0) {
                  var13 = false;
               }
            }

            if (!var13) {
               if (var1) {
                  Set var37 = CollectionUtil.intersect(this.ED, var8);
                  if (!var37.isEmpty()) {
                     StringBuilder var38 = new StringBuilder();
                     var38.append(this.kS.getMethodSignature());
                     var38.append(": ");
                     var38.append(S.L("the following custom IR plugins keep on optimizing"));
                     var38.append(": ");
                     int var40 = 0;

                     for (IDOptimizer var46 : var37) {
                        if (var40 > 0) {
                           var38.append(", ");
                        }

                        var38.append(var46.getName());
                        var40++;
                     }

                     pC.warn(var38.toString());
                     if (!A) {
                        synchronized (brk.class) {
                           if (!A) {
                              A = true;
                              pC.warn(
                                 S.L(
                                    "It is recommended to troubleshoot or disable the above plugins, as they seem to prevent full optimizations to take place."
                                 )
                              );
                           }
                        }
                     }
                  }

                  String var39 = "IR opt round exceeding threshold";
                  com.pnfsoftware.jeb.corei.parsers.dexdec.rQ.pC(new RuntimeException(var39), this.kS.getMethodSignature());
               }
               break;
            }
         }

         var8.clear();
         int var36 = 0;

         label444:
         for (IDOptimizer var15 : this.vP) {
            if ((var15 instanceof AbstractDOptimizer ? ((AbstractDOptimizer)var15).isEnabled() : !this.Sc.contains(var15))
               && var15.getType() != DOptimizerType.CUSTOM) {
               Object var16 = this.wS;
               if (var15.getType() == DOptimizerType.UNSAFE) {
                  if (this.sY) {
                     continue;
                  }

                  if (this.kS != null) {
                     if (pC(this.kS.getMethod())) {
                        continue;
                     }
                  } else if (this.wS != null) {
                     ArrayList var17 = new ArrayList();

                     for (IDMethodContext var19 : this.wS) {
                        if (pC(var19.getMethod())) {
                           var17.add(var19);
                        }
                     }

                     if (!var17.isEmpty()) {
                        var16 = new ArrayList(this.wS);
                        var16.removeAll(var17);
                     }
                  }
               }

               if ((this.ah == null || CollectionUtil.hasIntersection(this.ah, var15.getTags()))
                  && this.eP != null
                  && (this.eP.isEmpty() || !CollectionUtil.hasIntersection(this.eP, var15.getTags()))) {
                  if (var1) {
                     Watchdog.verify(this.kS.getWatchdog());
                  }

                  if (var9 != null && var9.contains(var15)) {
                     var10++;
                  } else {
                     if (var1) {
                        for (IDMasterOptimizerInstrumenter var45 : this.UO) {
                           int var47 = var45.beforePass(var15, this.kS);
                           if (var47 == -1) {
                              return var12;
                           }

                           if (var47 == 1) {
                              continue label444;
                           }

                           if (var47 != 0) {
                              throw new RuntimeException("Unknown return code for instrumenter beforePass() method: " + var47);
                           }
                        }
                     }

                     long var42 = System.currentTimeMillis();
                     int var48 = 0;

                     try {
                        if (var1) {
                           if (!var15.isCollectionOptimizer()) {
                              var48 = var15.perform(this.kS);
                           }
                        } else if (var15.isCollectionOptimizer()) {
                           var48 = var15.performOnCollection((List)var16, this.UT);
                        }
                     } catch (Exception var34) {
                        Exception var20 = var34;
                        boolean var21 = this.ED.contains(var15);
                        if (!var1) {
                           com.pnfsoftware.jeb.corei.parsers.dexdec.rQ.pC(
                              new RuntimeException("IR collection optimizer failed", var34),
                              null,
                              -1,
                              Maps.toMap(
                                 "external-dexdec-ir-optimizer",
                                 var21,
                                 "ir-collection",
                                 ((List)var16.stream().map(var0 -> var0.getMethodSignature()).collect(Collectors.toList())).toString()
                              )
                           );
                           pC.error(S.L("A collection optimizer failed, the decompiled code could not be entirely optimized"));
                        } else {
                           for (IDMasterOptimizerInstrumenter var23 : this.UO) {
                              int var24 = var23.afterFailedPass(var15, this.kS, var20);
                              if (var24 == -1) {
                                 return var12;
                              }

                              if (var24 != 0) {
                                 throw new RuntimeException("Unknown return code for instrumenter afterFailedPass() method: " + var24);
                              }
                           }

                           com.pnfsoftware.jeb.corei.parsers.dexdec.rQ.pC(
                              new RuntimeException("IR optimizer failed", var20),
                              this.kS.getMethodSignature(),
                              this.kS.getCfg().getInstructionCount(),
                              Maps.toMap("external-dexdec-ir-optimizer", var21)
                           );
                           pC.error(S.L("%s: an optimizer failed, the decompiled code could not be entirely optimized"), this.kS.getMethodSignature());
                        }

                        if (var21) {
                           pC.catchingSilent(var20);
                        }
                        break;
                     } finally {
                        long var28 = System.currentTimeMillis() - var42;
                        this.Ab += var28;
                     }

                     System.currentTimeMillis();
                     if (var48 > 0) {
                        var8.add(var15);
                        if (var1) {
                           int var49 = this.kS.getCfg().getInstructionCount();
                           var6 = var5;
                           var5 = var49;
                        }

                        if (var15.getTags().contains("deobfuscator")) {
                           int var50 = bpl.pC(var15);
                           if (var50 != 0 && var1) {
                              this.kS.incrementDeobfuscationScore(var48 * var50);
                           }
                        }
                     }

                     if (var1) {
                        for (IDMasterOptimizerInstrumenter var52 : this.UO) {
                           int var53 = var52.afterPass(var15, this.kS, var48);
                           if (var53 == -1) {
                              return var12;
                           }

                           if (var53 != 0) {
                              throw new RuntimeException("Unknown return code for instrumenter afterPass() method: " + var53);
                           }
                        }
                     }

                     if (var48 == 0 && var9 != null) {
                        var9.add(var15);
                     }

                     var36 += var48;
                     if (var2 && var48 > 0) {
                        break;
                     }
                  }
               }
            }
         }

         var12 += var36;
         var11++;
         if (var36 == 0) {
            if (var10 == 0 || var9 == null) {
               break;
            }

            Object[] var10000 = new Object[]{var10};
            var9.clear();
            var10 = 0;
         }
      }

      return var12;
   }

   private static boolean pC(IDexItem var0) {
      String var1 = var0.getDex().getFullComment(var0.getSignature());
      return var1 != null && var1.toLowerCase().contains("[no-unsafe-opt]");
   }

   public void pC(int var1) {
      this.E = var1;
   }

   @Override
   public void setSafeMode(boolean var1) {
      this.sY = var1;
   }

   @Override
   public boolean isSafeMode() {
      return this.sY;
   }

   @Override
   public void setPolicyForOptimizerTag(String var1, boolean var2) {
      if (var2) {
         if (var1 == null || var1.isEmpty()) {
            this.ah = new HashSet();
         } else if (var1.equals("*")) {
            this.ah = null;
         } else {
            if (this.ah != null) {
               this.ah.add(var1);
            }

            if (this.eP != null) {
               this.eP.remove(var1);
            }
         }
      } else if (var1 == null || var1.isEmpty()) {
         this.eP = new HashSet();
      } else if (var1.equals("*")) {
         this.eP = null;
      } else {
         if (this.eP != null) {
            this.eP.add(var1);
         }

         if (this.ah != null) {
            this.ah.remove(var1);
         }
      }
   }

   public int A() {
      return this.ys;
   }

   public void A(int var1) {
      if (var1 != this.ys) {
         this.ys = var1;
         bxp var2 = (bxp)this.findOptimizer(bxp.class);
         if (var2 != null) {
            var2.pC(this.ys);
         }
      }
   }

   public int kS() {
      return this.ld;
   }

   public void kS(int var1) {
      if (var1 != this.ld) {
         this.ld = var1;
         bxp var2 = (bxp)this.findOptimizer(bxp.class);
         if (var2 != null) {
            var2.A(this.ld);
         }
      }
   }

   public int wS() {
      return this.gp;
   }

   public void wS(int var1) {
      if (var1 != this.gp) {
         this.gp = var1;
         bzi var2 = (bzi)this.findOptimizer(bzi.class);
         if (var2 != null) {
            var2.pC(this.gp);
         }
      }
   }

   public int UT() {
      return this.oT;
   }

   public void UT(int var1) {
      if (var1 != this.oT) {
         this.oT = var1;
         bwv var2 = (bwv)this.findOptimizer(bwv.class);
         if (var2 != null) {
            var2.pC(this.oT);
         }
      }
   }

   public int E() {
      return this.fI;
   }

   public void E(int var1) {
      if (var1 != this.fI) {
         this.fI = var1;
         bww var2 = (bww)this.findOptimizer(bww.class);
         if (var2 != null) {
            var2.pC(this.fI);
         }
      }
   }

   public int sY() {
      return this.WR;
   }

   public void sY(int var1) {
      if (var1 != this.WR) {
         this.WR = var1;
         bzp var2 = (bzp)this.findOptimizer(bzp.class);
         if (var2 != null) {
            var2.pC(this.WR);
         }
      }
   }

   public int ys() {
      return this.NS;
   }

   public void ys(int var1) {
      if (var1 != this.NS) {
         this.NS = var1;
         bzn var2 = (bzn)this.findOptimizer(bzn.class);
         if (var2 != null) {
            var2.pC(this.NS);
         }
      }
   }
}
