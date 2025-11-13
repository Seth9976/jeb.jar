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

public class bvt implements IDMasterOptimizer {
   private static final ILogger RF = GlobalLog.getLogger(bvt.class);
   private static volatile boolean xK;
   private IDMethodContext Dw;
   private List Uv;
   private Map oW;
   private int gO = -1;
   private boolean nf = false;
   private int gP = 0;
   private int za = 0;
   private int lm = 0;
   private int zz = 0;
   private int JY = 0;
   private int HF = 0;
   private int LK = 0;
   private List io = new ArrayList();
   private List qa = new ArrayList();
   private List Hk = new ArrayList();
   private List Me = new ArrayList();
   private Set PV = null;
   private Set oQ = new HashSet();
   private List xW = new ArrayList();
   private long KT;
   public static final String q = "[no-unsafe-opt]";

   public bvt(IDMethodContext var1, boolean var2) {
      this.Dw = var1;
      if (var2) {
         this.za();
      }
   }

   public bvt(List var1, boolean var2) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.Uv = new ArrayList(var1);
         if (var2) {
            this.lm();
         }
      }
   }

   @Override
   public IDMethodContext getTarget() {
      return this.Dw;
   }

   @Override
   public boolean add(IDOptimizer var1) {
      return this.q(var1, true);
   }

   public boolean q(IDOptimizer var1, boolean var2) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else if (this.io.contains(var1)) {
         return false;
      } else {
         if (var2) {
            this.Hk.add(var1);
         } else {
            this.qa.add(var1);
         }

         this.io.add(var1);
         return true;
      }
   }

   @Override
   public boolean remove(IDOptimizer var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         if (this.io.contains(var1)) {
            if (this.qa.remove(var1)) {
               this.io.remove(var1);
               return true;
            }

            if (this.Hk.remove(var1)) {
               this.io.remove(var1);
               return true;
            }
         }

         return false;
      }
   }

   @Override
   public List getListOfOptimizers() {
      return Collections.unmodifiableList(this.io);
   }

   private void za() {
      this.io.add(new cen());
      this.io.add(new cem());
      this.io.add(new cer());
      this.io.add(new cfh());
      this.io.add(new cfi());
      this.io.add(new ceo());
      this.io.add(new cdb());
      this.io.add(new chh());
      this.io.add(new chf());
      this.io.add(new chg());
      this.io.add(new ces());
      this.io.add(new cbt());
      this.io.add(new cbv());
      this.io.add(new cbu());
      this.io.add(new cbp());
      this.io.add(new cfu());
      this.io.add(new cex());
      this.io.add(new cbq());
      this.io.add(new cbh());
      this.io.add(new cgg());
      this.io.add(new cga());
      this.io.add(new ccx());
      this.io.add(new cgi());
      this.io.add(new cfm());
      this.io.add(new cbz());
      this.io.add(new cca());
      this.io.add(new cfg());
      this.io.add(new cde());
      this.io.add(new cdf());
      this.io.add(new cdh());
      this.io.add(new cda());
      this.io.add(new ccu());
      this.io.add(new cfv());
      this.io.add(new cft());
      this.io.add(new cfy());
      this.io.add(new ccy());
      this.io.add(new cbo());
      this.io.add(new cgj());
      this.io.add(new cgv());
      this.io.add(new cdc());
      this.io.add(new ccs());
      this.io.add(new cgz());
      this.io.add(new chd());
      this.io.add(new cgk());
      this.io.add(new cgl());
      this.io.add(new cgt());
      this.io.add(new cgu());
      this.io.add(new chi());
      this.io.add(new ccv());
      this.io.add(new cgx());
      this.io.add(new cgd());
      this.io.add(new cgb());
      this.io.add(new cfr());
      this.io.add(new cct());
      this.io.add(new cdd());
      this.io.add(new ceu());
      this.io.add(new ccd());
      this.io.add(new ccc());
      this.io.add(new cfa());
      this.io.add(new cez());
      this.io.add(new cfn());
      this.io.add(new cgw());
      this.io.add(new cee());
      this.io.add(new cec());
      this.io.add(new cco());
      this.io.add(new cdq());
      this.io.add(new cce(this.xK(), this.Dw()));
      this.io.add(new chl());
      this.io.add(new chm());
      this.io.add(new cbw());
      this.io.add(new cfe());
      this.io.add(new cff());
      this.io.add(new cfo());
      this.io.add(new cdw());
      this.io.add(new cdv());
      this.io.add(new cfq());
      this.io.add(new cby());
      this.io.add(new chj());
      this.io.add(new cbg());
      this.io.add(new cfz());
      this.io.add(new cbj());
      this.io.add(new cbi());
      this.io.add(new ccw());
      this.io.add(new ccq());
      this.io.add(new cbr());
      this.io.add(new cho(true, false, true));
      this.io.add(new cbf());
      this.io.add(new chn());
      this.io.add(new cbk(this.oW()));
      this.io.add(new cbl(this.gO(), cbl.eo.xK));
      this.io.add(new cbx());
      this.io.add(new ccj());
      this.io.add(new cck());
      this.io.add(new cci());
      this.io.add(new cdg());
      this.io.add(new cdu());
      this.io.add(new cbs());
      this.io.add(new cek());
      this.io.add(new cel());
      this.io.add(new cei(this.nf()));
      this.io.add(new ceg(this.gP()));
      this.io.add(new cdz(this.Uv()));
      this.io.add(new cdn());
      this.io.add(new cdo());
      this.io.add(new cdt());
      this.io.add(new ccl());
      this.io.add(new cbd());
      this.io.add(new cbe());
      this.io.add(new cgh());
      this.io.add(new cgv());
      this.io.add(new cge());
      this.io.add(new chc());
      this.io.add(new cgf());
      this.io.add(new cdj());
      this.io.add(new cdl());
      this.io.add(new ccb());
      this.io.add(new chk());
   }

   private void lm() {
      this.io.add(new cbc());
   }

   @Override
   public IDOptimizer findOptimizer(Class var1) {
      for (IDOptimizer var3 : this.io) {
         if (var1.isInstance(var3)) {
            return var3;
         }
      }

      return null;
   }

   public boolean q(Class var1, boolean var2) {
      IDOptimizer var3 = this.findOptimizer(var1);
      if (var3 == null) {
         return false;
      } else {
         this.setOptimizerEnabled(var3, var2);
         return true;
      }
   }

   @Override
   public void setOptimizerEnabled(IDOptimizer var1, boolean var2) {
      if (var1 != null) {
         if (var1 instanceof AbstractDOptimizer) {
            ((AbstractDOptimizer)var1).setEnabled(var2);
         } else if (var2) {
            this.Me.remove(var1);
         } else {
            this.Me.add(var1);
         }
      }
   }

   @Override
   public void registerInstrumenter(IDMasterOptimizerInstrumenter var1) {
      if (var1 != null) {
         this.xW.add(var1);
      }
   }

   @Override
   public void unregisterInstrumenter(IDMasterOptimizerInstrumenter var1) {
      if (var1 != null) {
         this.xW.remove(var1);
      }
   }

   @Override
   public List getInstrumenters() {
      return Collections.unmodifiableList(this.xW);
   }

   public Map q() {
      return this.oW;
   }

   @Override
   public int perform() {
      this.KT = 0L;
      Object[] var10000 = new Object[0];
      long var1 = System.currentTimeMillis();

      int var3;
      try {
         var3 = this.zz();
      } finally {
         long var7 = System.currentTimeMillis() - var1;
         var10000 = new Object[]{var7, com.pnfsoftware.jeb.corei.parsers.dexdec.tw.q(this.KT)};
      }

      return var3;
   }

   private int zz() {
      this.io.sort(new bvu(this));
      this.oW = new HashMap();
      boolean var1 = this.Dw != null;
      boolean var2 = this.gO < 0;
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

      while (this.gO < 0 || var11 <= this.gO) {
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
                  Set var37 = CollectionUtil.intersect(this.Hk, var8);
                  if (!var37.isEmpty()) {
                     StringBuilder var38 = new StringBuilder();
                     var38.append(this.Dw.getMethodSignature());
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

                     RF.warn(var38.toString());
                     if (!xK) {
                        synchronized (bvt.class) {
                           if (!xK) {
                              xK = true;
                              RF.warn(
                                 S.L(
                                    "It is recommended to troubleshoot or disable the above plugins, as they seem to prevent full optimizations to take place."
                                 )
                              );
                           }
                        }
                     }
                  }

                  String var39 = "IR opt round exceeding threshold";
                  com.pnfsoftware.jeb.corei.parsers.dexdec.tw.q(new RuntimeException(var39), this.Dw.getMethodSignature());
               }
               break;
            }
         }

         var8.clear();
         int var36 = 0;

         label444:
         for (IDOptimizer var15 : this.io) {
            if ((var15 instanceof AbstractDOptimizer ? ((AbstractDOptimizer)var15).isEnabled() : !this.Me.contains(var15))
               && var15.getType() != DOptimizerType.CUSTOM) {
               Object var16 = this.Uv;
               if (var15.getType() == DOptimizerType.UNSAFE) {
                  if (this.nf) {
                     continue;
                  }

                  if (this.Dw != null) {
                     if (q(this.Dw.getMethod())) {
                        continue;
                     }
                  } else if (this.Uv != null) {
                     ArrayList var17 = new ArrayList();

                     for (IDMethodContext var19 : this.Uv) {
                        if (q(var19.getMethod())) {
                           var17.add(var19);
                        }
                     }

                     if (!var17.isEmpty()) {
                        var16 = new ArrayList(this.Uv);
                        var16.removeAll(var17);
                     }
                  }
               }

               if ((this.PV == null || CollectionUtil.hasIntersection(this.PV, var15.getTags()))
                  && this.oQ != null
                  && (this.oQ.isEmpty() || !CollectionUtil.hasIntersection(this.oQ, var15.getTags()))) {
                  if (var1) {
                     Watchdog.verify(this.Dw.getWatchdog());
                  }

                  if (var9 != null && var9.contains(var15)) {
                     var10++;
                  } else {
                     if (var1) {
                        for (IDMasterOptimizerInstrumenter var45 : this.xW) {
                           int var47 = var45.beforePass(var15, this.Dw);
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
                              var48 = var15.perform(this.Dw);
                           }
                        } else if (var15.isCollectionOptimizer()) {
                           var48 = var15.performOnCollection((List)var16, this.oW);
                        }
                     } catch (Exception var34) {
                        Exception var20 = var34;
                        boolean var21 = this.Hk.contains(var15);
                        if (!var1) {
                           com.pnfsoftware.jeb.corei.parsers.dexdec.tw.q(
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
                           RF.error(S.L("A collection optimizer failed, the decompiled code could not be entirely optimized"));
                        } else {
                           for (IDMasterOptimizerInstrumenter var23 : this.xW) {
                              int var24 = var23.afterFailedPass(var15, this.Dw, var20);
                              if (var24 == -1) {
                                 return var12;
                              }

                              if (var24 != 0) {
                                 throw new RuntimeException("Unknown return code for instrumenter afterFailedPass() method: " + var24);
                              }
                           }

                           com.pnfsoftware.jeb.corei.parsers.dexdec.tw.q(
                              new RuntimeException("IR optimizer failed", var20),
                              this.Dw.getMethodSignature(),
                              this.Dw.getCfg().getInstructionCount(),
                              Maps.toMap("external-dexdec-ir-optimizer", var21)
                           );
                           RF.error(S.L("%s: an optimizer failed, the decompiled code could not be entirely optimized"), this.Dw.getMethodSignature());
                        }

                        if (var21) {
                           RF.catchingSilent(var20);
                        }
                        break;
                     } finally {
                        long var28 = System.currentTimeMillis() - var42;
                        this.KT += var28;
                     }

                     System.currentTimeMillis();
                     if (var48 > 0) {
                        var8.add(var15);
                        if (var1) {
                           int var49 = this.Dw.getCfg().getInstructionCount();
                           var6 = var5;
                           var5 = var49;
                        }

                        if (var15.getTags().contains("deobfuscator")) {
                           int var50 = bto.q(var15);
                           if (var50 != 0 && var1) {
                              this.Dw.incrementDeobfuscationScore(var48 * var50);
                           }
                        }
                     }

                     if (var1) {
                        for (IDMasterOptimizerInstrumenter var52 : this.xW) {
                           int var53 = var52.afterPass(var15, this.Dw, var48);
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

   private static boolean q(IDexItem var0) {
      String var1 = var0.getDex().getFullComment(var0.getSignature());
      return var1 != null && var1.toLowerCase().contains("[no-unsafe-opt]");
   }

   private void JY() {
   }

   public static void q(IDMethodContext var0) {
   }

   public void q(int var1) {
      this.gO = var1;
   }

   public int RF() {
      return this.gO;
   }

   @Override
   public void setSafeMode(boolean var1) {
      this.nf = var1;
   }

   @Override
   public boolean isSafeMode() {
      return this.nf;
   }

   @Override
   public void setPolicyForOptimizerTag(String var1, boolean var2) {
      if (var2) {
         if (var1 == null || var1.isEmpty()) {
            this.PV = new HashSet();
         } else if (var1.equals("*")) {
            this.PV = null;
         } else {
            if (this.PV != null) {
               this.PV.add(var1);
            }

            if (this.oQ != null) {
               this.oQ.remove(var1);
            }
         }
      } else if (var1 == null || var1.isEmpty()) {
         this.oQ = new HashSet();
      } else if (var1.equals("*")) {
         this.oQ = null;
      } else {
         if (this.oQ != null) {
            this.oQ.add(var1);
         }

         if (this.PV != null) {
            this.PV.remove(var1);
         }
      }
   }

   public int xK() {
      return this.gP;
   }

   public void RF(int var1) {
      if (var1 != this.gP) {
         this.gP = var1;
         cce var2 = (cce)this.findOptimizer(cce.class);
         if (var2 != null) {
            var2.q(this.gP);
         }
      }
   }

   public int Dw() {
      return this.za;
   }

   public void xK(int var1) {
      if (var1 != this.za) {
         this.za = var1;
         cce var2 = (cce)this.findOptimizer(cce.class);
         if (var2 != null) {
            var2.RF(this.za);
         }
      }
   }

   public int Uv() {
      return this.lm;
   }

   public void Dw(int var1) {
      if (var1 != this.lm) {
         this.lm = var1;
         cdz var2 = (cdz)this.findOptimizer(cdz.class);
         if (var2 != null) {
            var2.q(this.lm);
         }
      }
   }

   public int oW() {
      return this.zz;
   }

   public void Uv(int var1) {
      if (var1 != this.zz) {
         this.zz = var1;
         cbk var2 = (cbk)this.findOptimizer(cbk.class);
         if (var2 != null) {
            var2.q(this.zz);
         }
      }
   }

   public int gO() {
      return this.JY;
   }

   public void oW(int var1) {
      if (var1 != this.JY) {
         this.JY = var1;
         cbl var2 = (cbl)this.findOptimizer(cbl.class);
         if (var2 != null) {
            var2.q(this.JY);
         }
      }
   }

   public int nf() {
      return this.HF;
   }

   public void gO(int var1) {
      if (var1 != this.HF) {
         this.HF = var1;
         cei var2 = (cei)this.findOptimizer(cei.class);
         if (var2 != null) {
            var2.q(this.HF);
         }
      }
   }

   public int gP() {
      return this.LK;
   }

   public void nf(int var1) {
      if (var1 != this.LK) {
         this.LK = var1;
         ceg var2 = (ceg)this.findOptimizer(ceg.class);
         if (var2 != null) {
            var2.q(this.LK);
         }
      }
   }
}
