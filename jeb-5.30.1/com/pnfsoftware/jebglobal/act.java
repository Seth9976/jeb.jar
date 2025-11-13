package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.CodeGapAnalysisStyle;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ICompiler;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.NativeAnalyzerException;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStructureTypeField;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeUtil;
import com.pnfsoftware.jeb.core.units.codeobject.IPECOFFUnit;
import com.pnfsoftware.jeb.core.units.codeobject.IPEDataDirectory;
import com.pnfsoftware.jeb.core.units.codeobject.IPEOptionalHeader;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class act extends IL {
   public act() {
      super(new aay.eo().q(ProcessorType.X86, ProcessorType.X86_64).q("winpe").q(ICompiler.COMP_VISUAL_STUDIO).q(CodeGapAnalysisStyle.PROLOGUES_ONLY).q());
   }

   @Override
   public boolean q(INativeCodeUnit var1) {
      try {
         if (var1.getCodeObjectContainer() instanceof IPECOFFUnit var3) {
            boolean var4 = var3.getLoaderInformation().getTargetProcessor().is64Bit();
            IPEOptionalHeader var5 = var3.getPEOptionalHeader();
            if (var5 != null) {
               if (var5.getMajorLinkerVersion() < 14) {
                  return false;
               }

               if (var5.getMajorLinkerVersion() == 14 && var5.getMinorLinkerVersion() < 20) {
                  return false;
               }

               IPEDataDirectory var6 = var3.getPEOptionalHeader().getDataDirectory()[10];
               if (var6.getPosition() > 0L && var6.getSize() > 0L) {
                  INativeType var7 = var1.getTypeManager().getType("IMAGE_LOAD_CONFIG_DIRECTORY" + (var4 ? "64" : "32"));
                  if (var7 != null) {
                     long var8 = var1.getVirtualImageBase() + var6.getPosition();
                     IStructureTypeField var10 = TypeUtil.getStructureField(var7, "GuardFlags");
                     if (var10 != null) {
                        int var11 = var1.getMemory().readInt(var8 + var10.getOffset());
                        if ((var11 & 8388608) != 0) {
                           return true;
                        }
                     }
                  }
               }
            }

            return false;
         }
      } catch (Exception var12) {
         JebCoreService.notifySilentExceptionToClient(new NativeAnalyzerException("C_INTEL_PE_MSVC_XFG parsing", var12));
      }

      return false;
   }
}
