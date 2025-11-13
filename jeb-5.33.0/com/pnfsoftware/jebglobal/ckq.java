package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.Version;
import com.pnfsoftware.jeb.core.units.code.asm.ChainedOperationResult;
import com.pnfsoftware.jeb.core.units.code.asm.INativeContext;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.AbstractNativeDecompilerExtension;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EState;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardPrototype;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;

public class ckq extends AbstractNativeDecompilerExtension {
   public ckq() {
      this.getPluginInformation().setName(S.L("UEFI Decompiler Extension Plugin"));
      this.getPluginInformation().setDescription(S.L("Helper for UEFI code decompilation"));
      this.getPluginInformation().setAuthor("PNF Software");
      this.getPluginInformation().setVersion(Version.create(0, 1));
   }

   @Override
   public ChainedOperationResult resolveVariableArgumentInformation(IERoutineContext var1, CFG var2, int var3, IWildcardPrototype var4) {
      if (this.pC(var1.getNativeContext()) && var4.getReturnType().toString().equals("EFI_STATUS")) {
         String var5 = var4.getSignature();
         if (var5.equals("EFI_STATUS(EFI_HANDLE*,...)") && this.pC(var1, var2, var3)) {
            throw new RuntimeException("TBI");
         }
      }

      return ChainedOperationResult.continue_();
   }

   private boolean pC(INativeContext var1) {
      return var1.getProcessor().getType() == ProcessorType.X86_64;
   }

   private boolean pC(IERoutineContext var1, CFG var2, int var3) {
      EState var4 = var1.getGlobalContext().buildState();
      BasicBlock var5 = var2.get(var3);

      for (int var6 = 0; var6 < var5.size() - 1; var6++) {
         IEStatement var7 = (IEStatement)var5.get(var6);
         IEGeneric var8 = null;
         if (var7 instanceof IEAssign) {
            IEGeneric var9 = ((IEAssign)var7).getDstOperand();
            if (var9 instanceof IEVar && ((IEVar)var9).getName().equals("r9")) {
               var8 = ((IEAssign)var7).getSrcOperand();
            }
         }

         if (var8 != null) {
            try {
               IEImm var11 = var8.evaluate(var4);
               if (var11.getValueAsLong() == 0L) {
                  return true;
               }
            } catch (Exception var10) {
            }
         }
      }

      return false;
   }
}
