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

public class ckc extends cjz {
   private static final ILogger Sc = GlobalLog.getLogger(ckc.class);
   private boolean ah = false;
   private Map eP = new HashMap();

   public ckc(INativeCodeAnalyzer var1) {
      super(var1);
   }

   private void pC() {
      INativeType var1 = this.pC("RUNTIME_FUNCTION");
      if (var1 instanceof IStructureType) {
         ((IStructureType)var1).getFields().stream().forEach(var0 -> ((ayu)var0).pC(1));
         IStructureTypeField var2 = ((IStructureType)var1).getField(2);
         if (var2.getType() instanceof IStructureType) {
            ((IStructureType)var2.getType()).getFields().stream().forEach(var0 -> ((ayu)var0).pC(1));
         }
      }

      INativeType var8 = this.pC("FuncInfo");
      if (var8 instanceof IStructureType) {
         this.pC((IStructureType)var8, "dispUnwindMap");
         this.pC((IStructureType)var8, "dispTryBlockMap");
         this.pC((IStructureType)var8, "dispIPtoStateMap");
         this.pC((IStructureType)var8, "dispESTypeList");
      }

      INativeType var3 = this.pC("UnwindMapEntry");
      if (var3 instanceof IStructureType) {
         this.pC((IStructureType)var3, "action");
      }

      INativeType var4 = this.pC("TryBlockMapEntry");
      if (var4 instanceof IStructureType) {
         this.pC((IStructureType)var4, "dispHandlerArray");
      }

      INativeType var5 = this.pC("HandlerType");
      if (var5 instanceof IStructureType) {
         this.pC((IStructureType)var5, "dispType");
         this.pC((IStructureType)var5, "dispOfHandler");
      }

      INativeType var6 = this.pC("IptoStateMapEntry");
      if (var6 instanceof IStructureType) {
         this.pC((IStructureType)var6, "Ip");
      }

      INativeType var7 = this.pC("ScopeTableEntry");
      if (var7 instanceof IStructureType) {
         ((IStructureType)var7).getFields().stream().forEach(var0 -> ((ayu)var0).pC(1));
      }
   }

   private INativeType pC(String var1) {
      INativeType var2 = this.pC.getTypeManager().getType(var1);
      if (var2 instanceof IAliasType) {
         var2 = ((IAliasType)var2).getAliasedType();
      }

      return var2;
   }

   private void pC(IStructureType var1, String var2) {
      ayu var3 = (ayu)var1.getFieldByName(var2);
      if (var3 != null) {
         var3.pC(1);
      }
   }

   @Override
   public boolean pC(boolean var1, boolean var2, boolean var3) throws MemoryException {
      if (this.kS == null || !this.kS.getLoaderInformation().getTargetProcessor().is64Bit() || this.kS.getPEOptionalHeader() == null) {
         return false;
      } else if (this.ah) {
         return false;
      } else {
         this.pC();
         ArrayList var4 = new ArrayList();
         long var5 = ((a)this.pC).wS().NS();
         IPEDataDirectory var7 = this.kS.getPEOptionalHeader().getDataDirectory()[3];
         if (var7.getPosition() > 0L && var7.getSize() > 0L) {
            int var8 = (int)var7.getSize();
            byte[] var9 = new byte[var8];
            long var10 = var5 + var7.getPosition();
            if (this.wS.read(var10, var8, var9, 0) == var8) {
               ByteBuffer var12 = ByteBuffer.wrap(var9);
               var12.order(ByteOrder.LITTLE_ENDIAN);

               while (var12.remaining() >= 12) {
                  cjs var13 = cjs.pC(this, var12, var10, var5, var2);
                  if (var13 == null) {
                     return false;
                  }

                  var4.add(var13);
                  var10 += 12L;
               }
            }
         }

         for (cjs var16 : var4) {
            this.pC(var16, 0);
         }

         if (this.pC.needsAnalysis()) {
            this.pC.analyze();
         }

         for (cjs var17 : var4) {
            this.pC(var17, 0, var5, var2, var3);
         }

         if (this.pC.needsAnalysis()) {
            this.pC.analyze();
         }

         this.ah = true;
         return true;
      }
   }

   private void pC(cjs var1, int var2) {
      if (var2 == 5) {
         cjo.pC("x64 SEH parsing -- chained unwind recursion during EP queueing");
      } else {
         cjx var3 = var1.A;
         if (var3.pC == 4) {
            if ((var3.pC & 3) != 0) {
               cjo.pC("x64 SEH parsing -- chained unwind");
               return;
            }

            this.pC(var3.UT, var2 + 1);
         } else if (var3.pC == 0) {
            cjo.kS(this.pC, var1.pC);
         } else if ((var3.pC & 3) != 0) {
            cjo.A(this.pC, var1.pC);
            cjo.A(this.pC, var1.A.A);
         }
      }
   }

   private void pC(cjs var1, int var2, long var3, boolean var5, boolean var6) throws MemoryException {
      if (var2 == 5) {
         cjo.pC("x64 SEH parsing -- chained unwind recursion during exception handler data parsing");
      } else if (var1.A.pC == 4) {
         this.pC(var1.A.UT, var2 + 1, var3, var5, var6);
      } else {
         if (var1.A.pC()) {
            auu var7 = ((Tw)this.pC.getModel()).E(var1.pC);
            if (var7 == null) {
               return;
            }

            auu var8 = ((Tw)this.pC.getModel()).E(var1.A.A);
            if (var8 == null) {
               return;
            }

            boolean var9 = true;
            if (cjo.pC(var8, "__CxxFrameHandler3")) {
               long var10 = var3 + var1.A.kS;
               cjp var12 = (cjp)this.eP.get(var10);
               if (var12 == null) {
                  var12 = cjp.pC(this, var10, var3, var5);
                  this.eP.put(var10, var12);
               } else {
                  var9 = false;
               }

               if (var12 == null) {
                  return;
               }

               if (!this.pC(var7, var12, var9)) {
                  return;
               }
            } else if (cjo.pC(var8, "__GSHandlerCheck_EH")) {
               long var13 = var3 + var1.A.kS;
               cjp var16 = (cjp)this.eP.get(var13);
               if (var16 == null) {
                  var16 = cjp.pC(this, var13, var3, var5);
                  this.eP.put(var13, var16);
               } else {
                  var9 = false;
               }

               if (var16 == null) {
                  return;
               }

               if (!this.pC(var7, var16, var9)) {
                  return;
               }
            } else if (cjo.pC(var8, "__GSHandlerCheck_SEH")) {
               long var14 = var1.A.wS;
               cka var17 = cka.pC(this, var14, var3, var5);
               if (!this.pC(var7, var17)) {
                  return;
               }
            } else if (cjo.pC(var8, "__C_specific_handler")) {
               long var15 = var1.A.wS;
               cka var18 = cka.pC(this, var15, var3, var5);
               if (!this.pC(var7, var18)) {
                  return;
               }
            }

            if (var6 && var9) {
               cjo.pC(this.pC, var7);
            }
         }
      }
   }

   private boolean pC(auu var1, cjp var2, boolean var3) {
      if (var3) {
         var2.pC(this.pC);
      }

      HashMap var4 = new HashMap();
      Couple var5 = null;

      for (cjr var7 : var2.kS) {
         int var8 = var7.A;
         long var9 = var7.pC;
         if (var5 != null && var5.getSecond() == null) {
            INativeContinuousItem var11 = this.pC.getModel().getPreviousItem(var9);
            if (var11 == null || !(var11 instanceof INativeInstructionItem)) {
               cjo.pC("cannot find try end address");
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

      aby var13 = var2.pC(this.pC, var4);
      if (var13 != null) {
         var1.pC(var13);
      }

      return true;
   }

   private boolean pC(auu var1, cka var2) {
      aby var3 = new aby(abq.kS);

      for (ckb var5 : var2.A) {
         INativeContinuousItem var6 = this.pC.getModel().getPreviousItem(var5.A);
         if (var6 != null && var6 instanceof INativeInstructionItem) {
            long var7 = var5.pC;
            long var9 = var6.getMemoryAddress();
            if (cjo.wS(this.pC, var5.kS)) {
               cjo.pC(this.pC, var5.kS);
               if (var5.pC()) {
                  var3.pC(abx.pC(var7, var9, abw.pC(var5.kS)));
               }
            }

            if (var5.wS != 0L && cjo.wS(this.pC, var5.wS)) {
               cjo.pC(this.pC, var5.wS);
               var3.pC(abx.pC(var7, var9, abw.pC(var5.wS, new cjt(var5.kS))));
            }
         } else {
            cjo.pC("cannot find try end address");
         }
      }

      if (!var3.kS()) {
         var1.pC(var3);
      }

      return true;
   }
}
