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

public class bgz implements IDexContextInfoProvider {
   private static final ILogger pC = GlobalLog.getLogger(bgz.class);
   private final com.pnfsoftware.jeb.corei.parsers.dex.vi A;
   private final int kS;
   private final long wS;
   private volatile Map UT;
   private volatile int E = 0;
   private volatile boolean sY = false;
   private volatile Thread ys;
   private final Set ld = new ConcurrentHashSet();
   private volatile bgw gp;
   private bgx oT;

   public bgz(IDexUnit var1, int var2, long var3) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.A = (com.pnfsoftware.jeb.corei.parsers.dex.vi)var1;
         if (var2 < 0) {
            var2 = 10000;
         }

         this.kS = var2;
         this.wS = var3;
         if (var3 >= 0L) {
            this.UT = new ConcurrentHashMap();
         } else {
            this.UT = new HashMap();
         }
      }
   }

   public boolean pC() {
      if (this.ys != null && this.ys.isAlive()) {
         this.ys.interrupt();

         try {
            this.ys.join(1000L);
         } catch (InterruptedException var1) {
         }

         return !this.ys.isAlive();
      } else {
         return true;
      }
   }

   private void A() {
      if (this.E == 0 || this.E == 2 && this.sY) {
         synchronized (this) {
            boolean var2 = true;
            if (this.ys != null) {
               if (this.ys.isAlive()) {
                  var2 = false;
               } else {
                  this.ys = null;
               }
            }

            if (var2) {
               if (this.E == 2 && this.sY) {
                  this.E = 0;
               }

               if (this.E == 0) {
                  this.sY = false;
                  this.E = 1;
                  if (this.wS < 0L) {
                     this.kS();
                  } else {
                     this.ld.clear();
                     this.ys = ThreadUtil.start("jeb-dex-cidb-builder", this::kS);
                  }
               }
            }
         }
      }

      if (this.E == 1 && this.wS > 0L) {
         Thread var1 = this.ys;
         if (var1 != null && var1.isAlive() && Thread.currentThread() != var1 && this.ld.add(Thread.currentThread().getId())) {
            try {
               var1.join(this.wS);
            } catch (InterruptedException var4) {
               throw new RuntimeException(var4);
            }
         }
      }
   }

   private void kS() {
      pC.trace("Generating dex cidb...");
      long var1 = System.currentTimeMillis();

      try {
         this.wS();
      } catch (InterruptionException var5) {
         pC.debug("Interruption: %s", var5.getMessage());
         return;
      }

      long var3 = System.currentTimeMillis() - var1;
      pC.trace("Generated dex cidb in %s", TimeFormatter.formatTimestampDelta(var3));
      this.E = 2;
   }

   private boolean wS() {
      if (this.UT.size() >= this.kS) {
         return false;
      } else {
         this.UT.clear();
         this.oT = this.A.Er();

         boolean var2;
         try {
            List var1 = this.oT.A();
            if (var1 != null) {
               for (int var3 : var1) {
                  this.kS(var3);
               }

               for (bgz.Av var13 : this.UT.values()) {
                  if (var13.A != null) {
                     for (int var5 : var13.A) {
                        bgz.Av var6 = (bgz.Av)this.UT.get(var5);
                        if (var6 != null) {
                           var13.pC = var13.pC | var6.pC;
                        }
                     }

                     var13.A = null;
                  }
               }

               return true;
            }

            var2 = false;
         } finally {
            this.oT = null;
         }

         return var2;
      }
   }

   private void UT() {
      if (this.gp == null) {
         synchronized (this) {
            if (this.gp == null) {
               bgw var2 = new bgw();
               this.gp = var2;
            }
         }
      }
   }

   public static ContextAccessType pC(int var0) {
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

   private ContextAccessType A(int var1) {
      bgz.Av var2 = (bgz.Av)this.UT.get(var1);
      return var2 == null ? ContextAccessType.UNKNOWN : pC(var2.pC);
   }

   public static int pC(ContextAccessType var0) {
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

   private int kS(int var1) {
      bfu var2 = this.A.sY(var1);
      if (var2 == null) {
         throw new IllegalArgumentException();
      } else {
         bgd var3 = var2.A();
         if (var3 == null) {
            return 128;
         } else {
            bev var4 = var3.A();
            return var4 == null ? 128 : this.pC(var1, var4);
         }
      }
   }

   private int pC(int var1, IDexCodeItem var2) {
      if (Thread.interrupted()) {
         throw new InterruptionException(S.L("Interrupted thread (most likely due to dex unit disposal)"));
      } else {
         bgz.Av var3 = (bgz.Av)this.UT.get(var1);
         if (var3 != null) {
            return var3.A != null && !var3.A.isEmpty() ? -1 : var3.pC;
         } else {
            ContextAccessType var4 = this.pC(this.A.sY(var1).getSignature(false));
            if (var4 != null && var4 != ContextAccessType.UNKNOWN) {
               var3 = new bgz.Av(pC(var4));
               this.UT.put(var1, var3);
               return var3.pC;
            } else if (Boolean.TRUE.equals(this.oT.pC(var1))) {
               var3 = new bgz.Av(pC(ContextAccessType.UNKNOWN));
               this.UT.put(var1, var3);
               return var3.pC;
            } else {
               var3 = new bgz.Av(-1);
               this.UT.put(var1, var3);
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
                        int var10 = this.oT.pC(var1, var7.getOffset());
                        int var16;
                        if (var10 >= 0) {
                           var16 = this.kS(var10);
                           if (var16 == -1) {
                              var3.pC(var10);
                              var16 = 0;
                           }
                        } else {
                           var10 = (int)var7.getParameter(0).getValue();
                           String var11 = this.A.sY(var10).getSignature(false);
                           ContextAccessType var12 = this.getMethodCAT(var11);
                           var16 = pC(var12);
                        }

                        var5 |= var16;
                        break;
                     case 115:
                     default:
                        int var9 = ((bek)var7).pC().UT & 0xFF0000;
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

               var3.pC = var5;
               return var5;
            }
         }
      }
   }

   @Override
   public boolean setMethodCAT(String var1, ContextAccessType var2) {
      this.UT();
      boolean var3 = this.gp.setMethodCAT(var1, var2);
      if (var3) {
         this.sY = true;
      }

      return var3;
   }

   private ContextAccessType pC(String var1) {
      if (this.gp != null) {
         ContextAccessType var2 = this.gp.getMethodCAT(var1);
         if (var2 != null && var2 != ContextAccessType.UNKNOWN) {
            return var2;
         }
      }

      return this.A.FE().getMethodCAT(var1);
   }

   @Override
   public ContextAccessType getMethodCAT(String var1) {
      this.A();
      ContextAccessType var2 = this.pC(var1);
      if (var2 != null && var2 != ContextAccessType.UNKNOWN) {
         return var2;
      } else {
         bfu var3 = this.A.wS(var1);
         if (var3 != null && var3.A() != null) {
            var2 = this.A(var3.getIndex());
            if (var2 != null) {
               return var2;
            }
         }

         return ContextAccessType.UNKNOWN;
      }
   }

   @Override
   public boolean setFieldEFInfo(String var1, EffectiveFinalityType var2) {
      this.UT();
      boolean var3 = this.gp.setFieldEFInfo(var1, var2);
      if (var3) {
         this.sY = true;
      }

      return var3;
   }

   private EffectiveFinalityType A(String var1) {
      if (this.gp != null) {
         EffectiveFinalityType var2 = this.gp.getFieldEFInfo(var1);
         if (var2 != null && var2 != EffectiveFinalityType.UNKNOWN) {
            return var2;
         }
      }

      return this.A.FE().getFieldEFInfo(var1);
   }

   @Override
   public EffectiveFinalityType getFieldEFInfo(String var1) {
      this.A();
      EffectiveFinalityType var2 = this.A(var1);
      if (var2 != null && var2 != EffectiveFinalityType.UNKNOWN) {
         return var2;
      } else {
         bft var3 = this.A.kS(var1);
         return var3 != null && var3.A() != null && var3.A().isFinal() ? EffectiveFinalityType.FINAL : EffectiveFinalityType.UNKNOWN;
      }
   }

   static class Av {
      int pC;
      Set A;

      Av(int var1) {
         this.pC = var1;
      }

      void pC(int var1) {
         if (this.A == null) {
            this.A = new HashSet();
         }

         this.A.add(var1);
      }
   }
}
