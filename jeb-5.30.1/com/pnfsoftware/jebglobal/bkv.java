package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.exceptions.InterruptionException;
import com.pnfsoftware.jeb.core.units.code.android.ContextAccessType;
import com.pnfsoftware.jeb.core.units.code.android.EffectiveFinalityType;
import com.pnfsoftware.jeb.core.units.code.android.IDexContextInfoProvider;
import com.pnfsoftware.jeb.core.units.code.android.IDexUnit;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDalvikInstruction;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexCodeItem;
import com.pnfsoftware.jeb.util.collect.ConcurrentHashSet;
import com.pnfsoftware.jeb.util.concurrent.ThreadUtil;
import com.pnfsoftware.jeb.util.format.TimeFormatter;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class bkv implements IDexContextInfoProvider {
   private static final ILogger HF = GlobalLog.getLogger(bkv.class);
   public static final int q = 10000;
   static final int RF = 1;
   static final int xK = 2;
   static final int Dw = 4;
   static final int Uv = 8;
   static final int oW = 16;
   static final int gO = 32;
   static final int nf = 64;
   static final int gP = 128;
   static final int za = 7;
   static final int lm = 24;
   static final int zz = 96;
   static final int JY = 127;
   private final com.pnfsoftware.jeb.corei.parsers.dex.bK LK;
   private final int io;
   private final long qa;
   private volatile Map Hk;
   private volatile int Me = 0;
   private volatile boolean PV = false;
   private volatile Thread oQ;
   private final Set xW = new ConcurrentHashSet();
   private volatile bks KT;
   private bkt Gf;

   public bkv(IDexUnit var1, int var2, long var3) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.LK = (com.pnfsoftware.jeb.corei.parsers.dex.bK)var1;
         if (var2 < 0) {
            var2 = 10000;
         }

         this.io = var2;
         this.qa = var3;
         if (var3 >= 0L) {
            this.Hk = new ConcurrentHashMap();
         } else {
            this.Hk = new HashMap();
         }
      }
   }

   public boolean q() {
      if (this.oQ != null && this.oQ.isAlive()) {
         this.oQ.interrupt();

         try {
            this.oQ.join(1000L);
         } catch (InterruptedException var1) {
         }

         return !this.oQ.isAlive();
      } else {
         return true;
      }
   }

   public int RF() {
      return this.Me;
   }

   private void Dw() {
      if (this.Me == 0 || this.Me == 2 && this.PV) {
         synchronized (this) {
            boolean var2 = true;
            if (this.oQ != null) {
               if (this.oQ.isAlive()) {
                  var2 = false;
               } else {
                  this.oQ = null;
               }
            }

            if (var2) {
               if (this.Me == 2 && this.PV) {
                  this.Me = 0;
               }

               if (this.Me == 0) {
                  this.PV = false;
                  this.Me = 1;
                  if (this.qa < 0L) {
                     this.Uv();
                  } else {
                     this.xW.clear();
                     this.oQ = ThreadUtil.start("jeb-dex-cidb-builder", this::Uv);
                  }
               }
            }
         }
      }

      if (this.Me == 1 && this.qa > 0L) {
         Thread var1 = this.oQ;
         if (var1 != null && var1.isAlive() && Thread.currentThread() != var1 && this.xW.add(Thread.currentThread().getId())) {
            try {
               var1.join(this.qa);
            } catch (InterruptedException var4) {
               throw new RuntimeException(var4);
            }
         }
      }
   }

   private void Uv() {
      HF.trace("Generating dex cidb...");
      long var1 = System.currentTimeMillis();

      try {
         this.oW();
      } catch (InterruptionException var5) {
         HF.debug("Interruption: %s", var5.getMessage());
         return;
      }

      long var3 = System.currentTimeMillis() - var1;
      HF.trace("Generated dex cidb in %s", TimeFormatter.formatTimestampDelta(var3));
      this.Me = 2;
   }

   private boolean oW() {
      if (this.Hk.size() >= this.io) {
         return false;
      } else {
         this.Hk.clear();
         this.Gf = this.LK.Rr();

         boolean var2;
         try {
            List var1 = this.Gf.RF();
            if (var1 != null) {
               for (int var3 : var1) {
                  this.xK(var3);
               }

               for (bkv.eo var13 : this.Hk.values()) {
                  if (var13.RF != null) {
                     for (int var5 : var13.RF) {
                        bkv.eo var6 = (bkv.eo)this.Hk.get(var5);
                        if (var6 != null) {
                           var13.q = var13.q | var6.q;
                        }
                     }

                     var13.RF = null;
                  }
               }

               return true;
            }

            var2 = false;
         } finally {
            this.Gf = null;
         }

         return var2;
      }
   }

   private void gO() {
      if (this.KT == null) {
         synchronized (this) {
            if (this.KT == null) {
               bks var2 = new bks();
               this.KT = var2;
            }
         }
      }
   }

   public static ContextAccessType q(int var0) {
      if (var0 == 0) {
         return ContextAccessType.NONE_SAFE;
      } else if (var0 >= 128) {
         return ContextAccessType.UNKNOWN;
      } else {
         boolean var1 = (var0 & 24) == 0;
         boolean var2 = (var0 & 96) == 0;
         return ContextAccessType.get(var1, var2);
      }
   }

   private ContextAccessType RF(int var1) {
      bkv.eo var2 = (bkv.eo)this.Hk.get(var1);
      return var2 == null ? ContextAccessType.UNKNOWN : q(var2.q);
   }

   public static int q(ContextAccessType var0) {
      switch (var0) {
         case NONE_SAFE:
            return 0;
         case NONE:
            return 7;
         case READ_ONLY:
            return 24;
         case WRITE_ONLY:
            return 96;
         case READ_WRITE:
            return 127;
         case UNKNOWN:
            return 128;
         default:
            throw new RuntimeException();
      }
   }

   private int xK(int var1) {
      bjp var2 = this.LK.gO(var1);
      if (var2 == null) {
         throw new IllegalArgumentException();
      } else {
         bjy var3 = var2.RF();
         if (var3 == null) {
            return 128;
         } else {
            bip var4 = var3.RF();
            return var4 == null ? 128 : this.q(var1, var4);
         }
      }
   }

   private int q(int var1, IDexCodeItem var2) {
      if (Thread.interrupted()) {
         throw new InterruptionException(S.L("Interrupted thread (most likely due to dex unit disposal)"));
      } else {
         bkv.eo var3 = (bkv.eo)this.Hk.get(var1);
         if (var3 != null) {
            return var3.RF != null && !var3.RF.isEmpty() ? -1 : var3.q;
         } else {
            ContextAccessType var4 = this.q(this.LK.gO(var1).getSignature(false));
            if (var4 != null && var4 != ContextAccessType.UNKNOWN) {
               var3 = new bkv.eo(q(var4));
               this.Hk.put(var1, var3);
               return var3.q;
            } else if (Boolean.TRUE.equals(this.Gf.xK(var1))) {
               var3 = new bkv.eo(q(ContextAccessType.UNKNOWN));
               this.Hk.put(var1, var3);
               return var3.q;
            } else {
               var3 = new bkv.eo(-1);
               this.Hk.put(var1, var3);
               int var5 = 0;

               for (IDalvikInstruction var7 : var2.getInstructions()) {
                  if (var5 >= 127) {
                     break;
                  }

                  int var8 = var7.getOpcode();
                  switch (var8) {
                     case 110:
                     case 111:
                     case 112:
                     case 113:
                     case 114:
                     case 116:
                     case 117:
                     case 118:
                     case 119:
                     case 120:
                        int var10 = this.Gf.q(var1, var7.getOffset());
                        int var16;
                        if (var10 >= 0) {
                           var16 = this.xK(var10);
                           if (var16 == -1) {
                              var3.q(var10);
                              var16 = 0;
                           }
                        } else {
                           var10 = (int)var7.getParameter(0).getValue();
                           String var11 = this.LK.gO(var10).getSignature(false);
                           ContextAccessType var12 = this.getMethodCAT(var11);
                           var16 = q(var12);
                        }

                        var5 |= var16;
                        break;
                     case 115:
                     default:
                        int var9 = ((bie)var7).q().Uv & 0xFF0000;
                        if (var9 != 0) {
                           if ((var9 & 524288) != 0) {
                              var9 &= -524289;
                              var5 |= 2;
                              switch (var7.getOpcode()) {
                                 case 39:
                                 case 147:
                                 case 148:
                                 case 158:
                                 case 159:
                                 case 179:
                                 case 180:
                                 case 190:
                                 case 191:
                                 case 211:
                                 case 212:
                                 case 219:
                                 case 220:
                                    var5 |= 128;
                              }
                           }

                           if (var9 != 0) {
                              if ((var9 & 65536) != 0) {
                                 var9 &= -65537;
                                 var5 |= 8;
                              }

                              if (var9 != 0) {
                                 if ((var9 & 131072) != 0) {
                                    var9 &= -131073;
                                    var5 |= 64;
                                 }

                                 if (var9 != 0) {
                                    if ((var9 & 262144) != 0) {
                                       var9 &= -262145;
                                       var5 |= 4;
                                    }

                                    if (var9 != 0) {
                                       if ((var9 & 1048576) != 0) {
                                          var9 &= -1048577;
                                          var5 |= 16;
                                       }

                                       if (var9 != 0) {
                                          if ((var9 & 2097152) != 0) {
                                             var9 &= -2097153;
                                             var5 |= 32;
                                          }

                                          if (var9 != 0 && (var9 & 196608) != 0) {
                                             var5 |= 128;
                                          }
                                       }
                                    }
                                 }
                              }
                           }
                        }
                  }
               }

               var3.q = var5;
               return var5;
            }
         }
      }
   }

   @Override
   public boolean setMethodCAT(String var1, ContextAccessType var2) {
      this.gO();
      boolean var3 = this.KT.setMethodCAT(var1, var2);
      if (var3) {
         this.PV = true;
      }

      return var3;
   }

   private ContextAccessType q(String var1) {
      if (this.KT != null) {
         ContextAccessType var2 = this.KT.getMethodCAT(var1);
         if (var2 != null && var2 != ContextAccessType.UNKNOWN) {
            return var2;
         }
      }

      return this.LK.EB().getMethodCAT(var1);
   }

   @Override
   public ContextAccessType getMethodCAT(String var1) {
      this.Dw();
      ContextAccessType var2 = this.q(var1);
      if (var2 != null && var2 != ContextAccessType.UNKNOWN) {
         return var2;
      } else {
         bjp var3 = this.LK.Dw(var1);
         if (var3 != null && var3.RF() != null) {
            var2 = this.RF(var3.getIndex());
            if (var2 != null) {
               return var2;
            }
         }

         return ContextAccessType.UNKNOWN;
      }
   }

   @Override
   public boolean setFieldEFInfo(String var1, EffectiveFinalityType var2) {
      this.gO();
      boolean var3 = this.KT.setFieldEFInfo(var1, var2);
      if (var3) {
         this.PV = true;
      }

      return var3;
   }

   private EffectiveFinalityType RF(String var1) {
      if (this.KT != null) {
         EffectiveFinalityType var2 = this.KT.getFieldEFInfo(var1);
         if (var2 != null && var2 != EffectiveFinalityType.UNKNOWN) {
            return var2;
         }
      }

      return this.LK.EB().getFieldEFInfo(var1);
   }

   @Override
   public EffectiveFinalityType getFieldEFInfo(String var1) {
      this.Dw();
      EffectiveFinalityType var2 = this.RF(var1);
      if (var2 != null && var2 != EffectiveFinalityType.UNKNOWN) {
         return var2;
      } else {
         bjo var3 = this.LK.xK(var1);
         return var3 != null && var3.RF() != null && var3.RF().isFinal() ? EffectiveFinalityType.FINAL : EffectiveFinalityType.UNKNOWN;
      }
   }

   public void xK() {
      this.PV = true;
   }

   static class eo {
      int q;
      Set RF;

      eo(int var1) {
         this.q = var1;
      }

      void q(int var1) {
         if (this.RF == null) {
            this.RF = new HashSet();
         }

         this.RF.add(var1);
      }
   }
}
