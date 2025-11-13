package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeDecompilerContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICConstantFactory;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICConstantString;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECall;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.emulator.EEmulator;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.emulator.MemoryWrites;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.opt.OptimizerType;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.core.units.code.asm.memory.VirtualMemoryUtil;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IPrototypeItem;
import com.pnfsoftware.jeb.core.units.code.asm.type.IReferenceType;
import com.pnfsoftware.jeb.core.units.code.asm.type.ITypeManager;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeLayoutInfo;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeUtil;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.HashMap;
import java.util.Map;

public class asm extends AbstractEOptimizer {
   int pC;
   INativeDecompilerContext A;
   ITypeManager kS;
   INativeType wS;
   INativeType UT;

   public asm() {
      this(1);
   }

   public asm(int var1) {
      this.setType(OptimizerType.UNSAFE);
      this.addTag("deobfuscator");
      this.getPluginInformation().setDescription("Generic decryptor for encrypted strings");
      this.pC = var1;
   }

   public void pC(int var1) {
      this.pC = var1;
   }

   @Override
   protected int perform() {
      if (this.pC == 0) {
         return 0;
      } else {
         if (this.kS == null) {
            this.A = this.ectx.getConverter().getDecompiler();
            this.kS = this.ectx.getWildcardTypeManager().getNativeTypeManager();
            this.wS = this.kS.getType("char");
            this.UT = this.kS.getType("wchar_t");
         }

         if (this.A == null) {
            return 0;
         } else {
            int var1 = 0;

            label186:
            for (BasicBlock var3 : this.cfg) {
               for (int var4 = 0; var4 < var3.size(); var4++) {
                  IEStatement var5 = (IEStatement)var3.get(var4);
                  if (var5.isCall()) {
                     IECall var6 = var5.asCall();
                     if (var6.isStaticCallsite() && var6.getCountOfReturns() == 1) {
                        INativeMethodItem var7 = var6.getStaticCallsite();

                        for (IEGeneric var9 : var6.getArguments()) {
                           if (!(var9 instanceof IEImm)) {
                              continue label186;
                           }
                        }

                        IWildcardType var26 = var6.getReturnExpression(0).getType();
                        int var27 = this.pC(var26);
                        if (var27 == 1) {
                           asm.Av var10 = (asm.Av)this.ectx.getGlobalContext().getData("ir_decryption_attempts");
                           if (var10 == null) {
                              var10 = new asm.Av();
                              this.ectx.getGlobalContext().setData("ir_decryption_attempts", var10);
                           }

                           String var11 = Strings.ff("0x%X", var7.getMemoryAddress());
                           asm.Av.Av var12 = (asm.Av.Av)var10.pC.get(var11);
                           if (var12 == null) {
                              var12 = new asm.Av.Av();
                              var10.pC.put(var11, var12);
                           }

                           if (var12.A < 2 && (var12.pC < 8 || var12.kS != 0)) {
                              var12.pC++;
                              EEmulator var13 = EEmulator.createStandard(this.ectx.getGlobalContext(), 10000);
                              var13.setResetUnknownRegisters(true);
                              var13.setRecordMemoryWrites(true);
                              var13.setup();
                              IPrototypeItem var14 = var6.getPrototype().resolve();
                              var13.setGlobalRoutineEmulation(var7.getMemoryAddress(), var14);

                              for (IEGeneric var16 : var6.getArguments()) {
                                 var13.addArgument((IEImm)var16);
                              }

                              long var28;
                              try {
                                 var13.run();
                                 MemoryWrites var17 = var13.getMemoryWrites();
                                 if (var17.getCountOfRecords() <= 0) {
                                    continue;
                                 }

                                 IEImm var18 = var13.readStorage(var14.getCallingConvention().getOutput(TypeLayoutInfo.ptr, 0));
                                 var28 = var18.getValueAsAddress();
                              } catch (Exception var24) {
                                 var12.A++;
                                 logger.catchingSilent(var24);
                                 continue;
                              } finally {
                                 var13.teardown();
                              }

                              if (var28 != 0L) {
                                 String var29 = VirtualMemoryUtil.readNullTerminatedStringSafe(var13.getVirtualMemory(), var28, 512);
                                 if (var29 != null && var29.length() >= 4) {
                                    var12.kS++;
                                    logger.info(S.L("0x%X: decrypted string: '%s'"), var28, var29);
                                    ICConstantFactory var30 = this.A.getHighLevelContext().getConstantFactory();
                                    ICConstantString var19 = var30.createString(var29, 0L);
                                    var19.setOrigin(S.L("Decrypted string"));
                                    IEImm var20 = this.ectx.createImm(-1L, this.ectx.getGlobalContext().getAddressBitsize()).duplicateToMutable();
                                    var20.setCustomAST(var19);
                                    IEAssign var21 = this.ectx.createAssign(var6.getReturnExpression(0), var20);
                                    var21.copyProperties(var5);
                                    var3.set(var4, var21);
                                    var1++;
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }

            if (var1 > 0) {
               this.cfg.invalidateDataFlowAnalysis();
            }

            return this.postPerform(var1);
         }
      }
   }

   private int pC(IWildcardType var1) {
      if (var1 == null) {
         return 0;
      } else {
         INativeType var2 = var1.getNativeType();
         if (var2 == null) {
            return 0;
         } else {
            var2 = TypeUtil.getNonAlias(var2);
            if (!(var2 instanceof IReferenceType)) {
               return 0;
            } else {
               INativeType var3 = ((IReferenceType)var2).getReferencedType();
               if (var3 instanceof IReferenceType) {
                  return 0;
               } else if (this.UT != null && TypeUtil.checkAliasedType(var3, this.UT)) {
                  return 2;
               } else {
                  if (this.wS != null) {
                     var3 = TypeUtil.getNonAlias(var3);
                     if (var3 == this.wS) {
                        return 1;
                     }
                  }

                  return 0;
               }
            }
         }
      }
   }

   static class Av {
      Map pC = new HashMap();

      static class Av {
         int pC;
         int A;
         int kS;
      }
   }
}
