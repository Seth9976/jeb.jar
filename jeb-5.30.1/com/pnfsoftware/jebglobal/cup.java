package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeInstructionItem;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.code.asm.type.IAliasType;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStructureType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStructureTypeField;
import com.pnfsoftware.jeb.core.units.codeobject.IPEDataDirectory;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class cup extends cum {
   private static final ILogger Hk = GlobalLog.getLogger(cup.class);
   private boolean Me = false;
   private Map PV = new HashMap();

   public cup(INativeCodeAnalyzer var1) {
      super(var1);
   }

   private void q() {
      INativeType var1 = this.q("RUNTIME_FUNCTION");
      if (var1 instanceof IStructureType) {
         ((IStructureType)var1).getFields().stream().forEach(var0 -> ((bbu)var0).q(1));
         IStructureTypeField var2 = ((IStructureType)var1).getField(2);
         if (var2.getType() instanceof IStructureType) {
            ((IStructureType)var2.getType()).getFields().stream().forEach(var0 -> ((bbu)var0).q(1));
         }
      }

      INativeType var8 = this.q("FuncInfo");
      if (var8 instanceof IStructureType) {
         this.q((IStructureType)var8, "dispUnwindMap");
         this.q((IStructureType)var8, "dispTryBlockMap");
         this.q((IStructureType)var8, "dispIPtoStateMap");
         this.q((IStructureType)var8, "dispESTypeList");
      }

      INativeType var3 = this.q("UnwindMapEntry");
      if (var3 instanceof IStructureType) {
         this.q((IStructureType)var3, "action");
      }

      INativeType var4 = this.q("TryBlockMapEntry");
      if (var4 instanceof IStructureType) {
         this.q((IStructureType)var4, "dispHandlerArray");
      }

      INativeType var5 = this.q("HandlerType");
      if (var5 instanceof IStructureType) {
         this.q((IStructureType)var5, "dispType");
         this.q((IStructureType)var5, "dispOfHandler");
      }

      INativeType var6 = this.q("IptoStateMapEntry");
      if (var6 instanceof IStructureType) {
         this.q((IStructureType)var6, "Ip");
      }

      INativeType var7 = this.q("ScopeTableEntry");
      if (var7 instanceof IStructureType) {
         ((IStructureType)var7).getFields().stream().forEach(var0 -> ((bbu)var0).q(1));
      }
   }

   private INativeType q(String var1) {
      INativeType var2 = this.q.getTypeManager().getType(var1);
      if (var2 instanceof IAliasType) {
         var2 = ((IAliasType)var2).getAliasedType();
      }

      return var2;
   }

   private void q(IStructureType var1, String var2) {
      bbu var3 = (bbu)var1.getFieldByName(var2);
      if (var3 != null) {
         var3.q(1);
      }
   }

   @Override
   public boolean q(boolean var1, boolean var2, boolean var3) throws MemoryException {
      if (this.xK == null || !this.xK.getLoaderInformation().getTargetProcessor().is64Bit() || this.xK.getPEOptionalHeader() == null) {
         return false;
      } else if (this.Me) {
         return false;
      } else {
         this.q();
         ArrayList var4 = new ArrayList();
         long var5 = ((aae)this.q).Dw().io();
         IPEDataDirectory var7 = this.xK.getPEOptionalHeader().getDataDirectory()[3];
         if (var7.getPosition() > 0L && var7.getSize() > 0L) {
            int var8 = (int)var7.getSize();
            byte[] var9 = new byte[var8];
            long var10 = var5 + var7.getPosition();
            if (this.Dw.read(var10, var8, var9, 0) == var8) {
               ByteBuffer var12 = ByteBuffer.wrap(var9);
               var12.order(ByteOrder.LITTLE_ENDIAN);

               while (var12.remaining() >= 12) {
                  cuf var13 = cuf.q(this, var12, var10, var5, var2);
                  if (var13 == null) {
                     return false;
                  }

                  var4.add(var13);
                  var10 += 12L;
               }
            }
         }

         for (cuf var16 : var4) {
            this.q(var16, 0);
         }

         if (this.q.needsAnalysis()) {
            this.q.analyze();
         }

         for (cuf var17 : var4) {
            this.q(var17, 0, var5, var2, var3);
         }

         if (this.q.needsAnalysis()) {
            this.q.analyze();
         }

         this.Me = true;
         return true;
      }
   }

   private void q(cuf var1, int var2) {
      if (var2 == 5) {
         cub.q("x64 SEH parsing -- chained unwind recursion during EP queueing");
      } else {
         cuk var3 = var1.xK;
         if (var3.oW == 4) {
            if ((var3.oW & 3) != 0) {
               cub.q("x64 SEH parsing -- chained unwind");
               return;
            }

            this.q(var3.za, var2 + 1);
         } else if (var3.oW == 0) {
            cub.xK(this.q, var1.RF);
         } else if ((var3.oW & 3) != 0) {
            cub.RF(this.q, var1.RF);
            cub.RF(this.q, var1.xK.gO);
         }
      }
   }

   private void q(cuf var1, int var2, long var3, boolean var5, boolean var6) throws MemoryException {
      if (var2 == 5) {
         cub.q("x64 SEH parsing -- chained unwind recursion during exception handler data parsing");
      } else if (var1.xK.oW == 4) {
         this.q(var1.xK.za, var2 + 1, var3, var5, var6);
      } else {
         if (var1.xK.q()) {
            axp var7 = ((aaf)this.q.getModel()).oW(var1.RF);
            if (var7 == null) {
               return;
            }

            axp var8 = ((aaf)this.q.getModel()).oW(var1.xK.gO);
            if (var8 == null) {
               return;
            }

            boolean var9 = true;
            if (cub.q(var8, "__CxxFrameHandler3")) {
               long var10 = var3 + var1.xK.nf;
               cuc var12 = (cuc)this.PV.get(var10);
               if (var12 == null) {
                  var12 = cuc.q(this, var10, var3, var5);
                  this.PV.put(var10, var12);
               } else {
                  var9 = false;
               }

               if (var12 == null) {
                  return;
               }

               if (!this.q(var7, var12, var9)) {
                  return;
               }
            } else if (cub.q(var8, "__GSHandlerCheck_EH")) {
               long var13 = var3 + var1.xK.nf;
               cuc var16 = (cuc)this.PV.get(var13);
               if (var16 == null) {
                  var16 = cuc.q(this, var13, var3, var5);
                  this.PV.put(var13, var16);
               } else {
                  var9 = false;
               }

               if (var16 == null) {
                  return;
               }

               if (!this.q(var7, var16, var9)) {
                  return;
               }
            } else if (cub.q(var8, "__GSHandlerCheck_SEH")) {
               long var14 = var1.xK.gP;
               cun var17 = cun.q(this, var14, var3, var5);
               if (!this.q(var7, var17)) {
                  return;
               }
            } else if (cub.q(var8, "__C_specific_handler")) {
               long var15 = var1.xK.gP;
               cun var18 = cun.q(this, var15, var3, var5);
               if (!this.q(var7, var18)) {
                  return;
               }
            }

            if (var6 && var9) {
               cub.q(this.q, var7);
            }
         }
      }
   }

   private boolean q(axp var1, cuc var2, boolean var3) {
      if (var3) {
         var2.q(this.q);
      }

      HashMap var4 = new HashMap();
      Couple var5 = null;

      for (cue var7 : var2.xK) {
         int var8 = var7.RF;
         long var9 = var7.q;
         if (var5 != null && var5.getSecond() == null) {
            INativeContinuousItem var11 = this.q.getModel().getPreviousItem(var9);
            if (var11 == null || !(var11 instanceof INativeInstructionItem)) {
               cub.q("cannot find try end address");
               continue;
            }

            ArrayList var12 = new ArrayList();
            var12.add(var11.getMemoryAddress());
            var5.setSecond(var12);
         }

         if (var4.containsKey(var8)) {
            var5 = null;
         } else if (var8 != -1) {
            var5 = new Couple(var9, null);
            var4.put(var8, var5);
         }
      }

      adq var13 = var2.q(this.q, var4);
      if (var13 != null) {
         var1.q(var13);
      }

      return true;
   }

   private boolean q(axp var1, cun var2) {
      adq var3 = new adq(adi.xK);

      for (cuo var5 : var2.RF) {
         INativeContinuousItem var6 = this.q.getModel().getPreviousItem(var5.RF);
         if (var6 != null && var6 instanceof INativeInstructionItem) {
            long var7 = var5.q;
            long var9 = var6.getMemoryAddress();
            if (cub.Dw(this.q, var5.xK)) {
               cub.q(this.q, var5.xK);
               if (var5.q()) {
                  var3.q(adp.q(var7, var9, ado.q(var5.xK)));
               }
            }

            if (var5.Dw != 0L && cub.Dw(this.q, var5.Dw)) {
               cub.q(this.q, var5.Dw);
               var3.q(adp.q(var7, var9, ado.q(var5.Dw, new cug(var5.xK))));
            }
         } else {
            cub.q("cannot find try end address");
         }
      }

      if (!var3.xK()) {
         var1.q(var3);
      }

      return true;
   }
}
