package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEJump;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IENop;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESwitch;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

public class alx {
   private static final StructuredLogger logger = aco.pC(alx.class);
   private IERoutineContext ctx;
   private CFG cfg;
   private CFG cfgN;
   private TreeMap interToInterN = new TreeMap();
   private List adjustedEntriesForNativeAddresses = new ArrayList();
   private List missingEntriesForNativeAddresses = new ArrayList();

   public alx(IERoutineContext var1) {
      this.ctx = var1;
   }

   public IERoutineContext getContext() {
      return this.ctx;
   }

   public IEStatement[] processStatement(IEStatement var1, int var2) {
      return new IEStatement[]{var1};
   }

   public boolean process() {
      return this.process(true, true, false);
   }

   public boolean process(boolean var1, boolean var2, boolean var3) {
      return this.process(var1, var2, var3, null);
   }

   public boolean process(boolean var1, boolean var2, boolean var3, Boolean var4) {
      if (this.cfgN != null) {
         throw new IllegalStateException("The graph was already processed");
      } else {
         this.cfg = this.ctx.getCfg();
         if (var4 == null) {
            var4 = (this.cfg.getFlags() & 1) != 0;
         }

         ArrayList var5 = new ArrayList();
         ArrayList var6 = new ArrayList();
         ArrayList var7 = new ArrayList();
         ArrayList var8 = new ArrayList();

         try {
            int var9 = 0;

            for (BasicBlock var11 : this.cfg) {
               int var12 = (int)var11.getFirstAddress();

               for (IEStatement var14 : var11) {
                  int var15 = var14.getSize();
                  IEStatement[] var16 = this.processStatement(var14, var12);
                  if (var16 == null || var16.length == 0) {
                     throw new RuntimeException("processStatements() must have at least one instruction, got: " + Arrays.toString((Object[])var16));
                  }

                  var14 = var16[0];
                  if (var1) {
                     var14.adjustSize(-var15 + 1);
                  }

                  IEStatement[] var17 = (IEStatement[])Arrays.copyOfRange(var16, 1, var16.length);
                  if (var1) {
                     for (IEStatement var21 : var17) {
                        if (var21.getSize() != 1) {
                           throw new RuntimeException("Inserted statement size must be 1");
                        }
                     }
                  }

                  if (var2) {
                     if (var14 instanceof IENop && var17.length > 0) {
                        throw new RuntimeException("Additional statements cannot be inserted when the first one is a nop and nops should be removed");
                     }

                     for (IEStatement var42 : var17) {
                        if (var42 instanceof IENop) {
                           throw new RuntimeException("Inserted statement cannot be nop");
                        }
                     }
                  }

                  if (var14 instanceof IEJump) {
                     var6.add((IEJump)var14);
                  } else if (var14 instanceof IESwitch) {
                     var7.add((IESwitch)var14);
                  }

                  if (var2 && var14 instanceof IENop) {
                     var8.add(var12);
                  } else {
                     if (!var8.isEmpty()) {
                        for (int var38 : var8) {
                           this.interToInterN.put(var38, var9);
                        }

                        var8.clear();
                     }

                     this.interToInterN.put(var12, var9);
                     var5.add(var14);
                     var9 += var14.getSize();

                     for (IEStatement var43 : var17) {
                        var5.add(var43);
                        var9 += var43.getSize();
                     }
                  }

                  var12 += var15;
               }
            }

            if (!var8.isEmpty()) {
               int var23 = (Integer)var8.remove(var8.size() - 1);

               for (int var30 : var8) {
                  this.interToInterN.put(var30, var9);
               }

               this.interToInterN.put(var23, var9);
               IENop var27 = this.ctx.createNop();
               var27.copyLowerLevelAddresses((IEStatement)this.cfg.getLast().get(0));
               var5.add(var27);
            }

            for (IEJump var28 : var6) {
               var28.setBranchAddress(this.updateIROffset(var28.getBranchAddress()));
            }

            for (IESwitch var29 : var7) {
               if (var29.hasDefaultAddress()) {
                  var29.setDefaultAddress(this.updateIROffset(var29.getDefaultAddress()));
               }

               for (Couple var32 : var29.getCases()) {
                  var32.setSecond(this.updateIROffset((Integer)var32.getSecond()));
               }
            }

            this.cfgN = this.ctx.buildCfg(var5, var4, false);
            this.cfgN.setVariableInformationProvider(this.cfg.getVariableInformationProvider());
            this.cfgN.setDFADefaultCollectionFlags(this.cfg.getDFADefaultCollectionFlags());
            if (var3) {
               this.ctx.setCfg(this.cfgN);
            }

            return true;
         } catch (Exception var22) {
            Object[] var10000 = new Object[0];
            logger.catching(var22);
            JebCoreService.notifySilentExceptionToClient(var22);
            return false;
         }
      }
   }

   private int updateIROffset(int var1) {
      Integer var2 = (Integer)this.interToInterN.get(var1);
      if (var2 == null) {
         throw new RuntimeException(Strings.ff("Cannot convert old IR offset to normalized one: 0x%X", var1));
      } else {
         return var2;
      }
   }

   public CFG getCfg() {
      if (this.cfgN == null) {
         throw new IllegalStateException();
      } else {
         return this.cfgN;
      }
   }

   @Deprecated
   public List getMissingEntriesForNativeAddresses() {
      return this.missingEntriesForNativeAddresses;
   }

   @Deprecated
   public List getAdjustedEntriesForNativeAddresses() {
      return this.adjustedEntriesForNativeAddresses;
   }
}
