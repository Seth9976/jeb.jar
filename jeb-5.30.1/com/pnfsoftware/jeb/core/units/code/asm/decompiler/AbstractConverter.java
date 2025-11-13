package com.pnfsoftware.jeb.core.units.code.asm.decompiler;

import com.pnfsoftware.jeb.client.Licensing;
import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.units.code.AddressableInstruction;
import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.IFlowInformation;
import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.INativeContext;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.DecompilerException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.UnsupportedConversionException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EBranchDetails;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.ELocation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EPrototypeHandler;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EState;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EVarCopyFinder;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEBranchDetails;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECall;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECond;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEJumpFar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEPrototypeHandler;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEReturn;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEUntranslatedInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardPrototype;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardTypeManager;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.SimulationPointInformation;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeInstructionItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.InstructionHints;
import com.pnfsoftware.jeb.core.units.code.asm.processor.IProcessor;
import com.pnfsoftware.jeb.core.units.code.asm.processor.IRegisterBank;
import com.pnfsoftware.jeb.core.units.code.asm.processor.RegisterDescriptionEntry;
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterUtil;
import com.pnfsoftware.jeb.core.units.code.asm.type.CallingConventionUtil;
import com.pnfsoftware.jeb.core.units.code.asm.type.ICallingConvention;
import com.pnfsoftware.jeb.core.units.code.asm.type.ICallingConventionManager;
import com.pnfsoftware.jeb.core.units.code.asm.type.IPrototypeItem;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStorageEntryGenerator;
import com.pnfsoftware.jeb.core.units.code.asm.type.ITypeManager;
import com.pnfsoftware.jeb.core.units.code.asm.type.StorageEntry;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.collect.Maps;
import com.pnfsoftware.jeb.util.collect.ReferenceCounter;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.Endianness;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import com.pnfsoftware.jeb.util.math.MathUtil;
import com.pnfsoftware.jeb.util.primitives.Booleans;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import com.pnfsoftware.jebglobal.aeb;
import com.pnfsoftware.jebglobal.aeg;
import com.pnfsoftware.jebglobal.alj;
import com.pnfsoftware.jebglobal.alk;
import com.pnfsoftware.jebglobal.aln;
import com.pnfsoftware.jebglobal.alr;
import com.pnfsoftware.jebglobal.aml;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Ser
public abstract class AbstractConverter implements IEConverter {
   private static final StructuredLogger logger = aeg.q(AbstractConverter.class);
   @SerId(1)
   private INativeDecompilerContext decompiler;
   @SerId(2)
   protected IEGlobalContext gCtx;
   @SerId(3)
   protected INativeContext nctx;
   @SerId(4)
   protected IProcessor proc;
   @SerId(5)
   protected int regNormalBitsize;
   @SerId(6)
   protected boolean doNotGenerateNops;
   @SerId(7)
   protected int methodConversionCountFailure;
   @SerId(8)
   protected int methodConversionCountSuccess;
   @SerId(9)
   ReferenceCounter missedInsnCounter;
   @SerId(10)
   int insnConversionCount;
   @SerId(11)
   int insnConversionCountFailure;
   @SerId(12)
   int insnConversionCountUntranslated;
   @SerTransient
   protected IERoutineContext ctx;
   @SerTransient
   protected Set parameterRegistersForAllCC;
   @SerTransient
   protected Set spoiledRegistersForAllCC;

   protected AbstractConverter(IProcessor var1) {
      this(var1, var1.getGPRegisterBitsize(), var1.getPCRegisterBitsize());
   }

   protected AbstractConverter(IProcessor var1, int var2) {
      this(var1, var2, var2);
   }

   protected AbstractConverter(IProcessor var1, int var2, int var3) {
      this.proc = var1;
      this.regNormalBitsize = var2;
      this.gCtx = new alr(this, var2, var3, var1.getEndianness().isBig());
   }

   protected AbstractConverter(INativeContext var1) {
      this(var1.getProcessor());
      this.nctx = var1;
   }

   @Override
   public void initialize() {
   }

   public void setNativeContext(INativeContext var1) {
      this.nctx = var1;
   }

   @Override
   public INativeContext getNativeContext() {
      return this.nctx;
   }

   @Override
   public IEGlobalContext getGlobalContext() {
      return this.gCtx;
   }

   public void setDoNotGenerateNops(boolean var1) {
      this.doNotGenerateNops = var1;
   }

   public boolean isDoNotGenerateNops() {
      return this.doNotGenerateNops;
   }

   public void setDecompiler(INativeDecompilerContext var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else if (this.decompiler != null && var1 != this.decompiler) {
         throw new IllegalArgumentException();
      } else {
         this.decompiler = var1;
      }
   }

   @Override
   public INativeDecompilerContext getDecompiler() {
      return this.decompiler;
   }

   protected final boolean insertOptionalEntryPointTrampoline(IERoutineContext var1, List var2) {
      CFG var3 = var1.getRoutine().getData().getCFG();
      long var4 = var3.getEntryAddress();
      long var6 = var3.getFirstAddress();
      if (var4 == var6) {
         return false;
      } else {
         IEAssign var8 = var1.createBranchAssign(this.getProgramCounter(), EUtil.imm(var4, this.getAddressBitsize()), false);
         var8.setLowerLevelAddress(-1L);
         var2.add(var8);
         return true;
      }
   }

   @Override
   public int getAddressBitsize() {
      return this.getProgramCounter().getBitsize();
   }

   @Override
   public int getRegisterBitsize() {
      return this.getAddressBitsize();
   }

   @Override
   public int getStackSlotSize() {
      return this.getRegisterBitsize() / 8;
   }

   @Override
   public IWildcardType.Group getWildcardTypeManagerDefaultResolutionGroup() {
      return IWildcardType.Group.INTEGER;
   }

   @Override
   public final IEGeneric getRegister(String var1) {
      return this.getRegister(var1, null);
   }

   @Override
   public IEGeneric getRegister(String var1, ELocation var2) {
      return this.gCtx.getVariableByName(var1);
   }

   @Override
   public final IEGeneric getRegisterVariableFromNativeRegisterId(long var1) {
      return this.getRegisterVariableFromNativeRegisterId(var1, null);
   }

   @Override
   public IEGeneric getRegisterVariableFromNativeRegisterId(long var1, ELocation var3) {
      IRegisterBank var4 = this.proc.getRegisterBank();
      if (var4 != null) {
         RegisterDescriptionEntry var5;
         if ((var1 & -65536L) == 0L) {
            var5 = var4.getDescriptionEntry((int)var1);
         } else {
            var5 = var4.getDescriptionEntryById(var1);
         }

         if (var5 != null) {
            for (String var7 : var5.getNames()) {
               IEGeneric var8 = this.getRegister(var7, var3);
               if (var8 != null) {
                  return var8;
               }
            }

            if (var5.isRegisterSlice()) {
               RegisterDescriptionEntry var11 = var5.getContainer();

               for (String var14 : var11.getNames()) {
                  IEGeneric var9 = this.getRegister(var14, var3);
                  if (var9 != null) {
                     return var9.slice(var5.getBitstart(), var5.getBitend());
                  }
               }
            }
         }
      }

      int var10 = RegisterUtil.getRegisterGroup(var1);
      int var12 = RegisterUtil.getRegisterIndex(var1);
      throw new DecompilerException(Strings.ff("Cannot convert native register id %d (grp:%d,index:%d) to its corresponding IEVar", var1, var10, var12));
   }

   @Override
   public long getNativeRegisterIdFromRegisterVariable(IEVar var1, boolean var2) {
      IRegisterBank var3 = this.proc.getRegisterBank();
      if (var3 != null) {
         RegisterDescriptionEntry var4 = var3.getDescriptionEntryByName(var1.getName());
         if (var4 != null) {
            if (var2) {
               return var4.getNumber();
            }

            return var4.getId();
         }
      }

      throw new DecompilerException(Strings.ff("Cannot convert variable '%s' to native register id", var1));
   }

   @Override
   public final long getNativeRegisterIdFromRegisterVariable(IEVar var1) {
      return this.getNativeRegisterIdFromRegisterVariable(var1, false);
   }

   @Override
   public String getSlicedRegisterName(String var1, int var2, int var3) {
      IRegisterBank var4 = this.proc.getRegisterBank();
      if (var4 != null) {
         RegisterDescriptionEntry var5 = var4.getDescriptionEntryByName(var1);
         if (var5 != null && var5.isPhysicalRegister()) {
            var5 = var5.getSlice(var2, var3);
            if (var5 != null) {
               return var5.getName();
            }
         }
      }

      return null;
   }

   public void setCurrentContext(IERoutineContext var1) {
      this.ctx = var1;
   }

   protected void preRoutineConversion(INativeMethodItem var1, IERoutineContext var2, List var3) {
      this.insertOptionalEntryPointTrampoline(var2, var3);
   }

   protected void postRoutineConversion(INativeMethodItem var1, IERoutineContext var2) {
   }

   @Override
   public IERoutineContext convert(INativeMethodItem var1) {
      IERoutineContext var2 = this.gCtx.createRoutineContext(var1);
      this.setCurrentContext(var2);

      IERoutineContext var17;
      try {
         CFG var3 = var1.getData().getCFG();
         long var4 = 0L;
         ArrayList var6 = new ArrayList();
         this.preRoutineConversion(var1, var2, var6);

         for (int var7 = 0; var7 < var3.size(); var7++) {
            BasicBlock var8 = var3.get(var7);
            if (Long.compareUnsigned(var8.getBase(), var4) >= 0) {
               BasicBlock var16 = this.preBlockConversion(var3, var8, var6);
               int var9 = var6.size();
               this.convertBlock(var16, var6);
               this.postBlockConversion(var3, var16, var6, var6.size() - var9);
               var4 = var16.getEndAddress();
            }
         }

         var2.setStatements(var6);
         this.postRoutineConversion(var1, var2);
         alk var15 = new alk(var2);
         var15.q();
         var2.setStatements(var6, true, true, true);
         this.gCtx.addRoutineContext(var2);
         this.methodConversionCountSuccess++;
         var17 = var2;
      } catch (Exception var13) {
         this.methodConversionCountFailure++;
         throw var13;
      } finally {
         this.setCurrentContext(null);
      }

      return var17;
   }

   protected BasicBlock preBlockConversion(CFG var1, BasicBlock var2, List var3) {
      return var2;
   }

   protected void convertBlock(BasicBlock var1, List var2) {
      long var3 = var1.getFirstAddress();
      long var5 = var3;
      ArrayList var7 = new ArrayList();
      ConverterInstructionEntry var8 = new ConverterInstructionEntry();
      var8.r = var7;
      IInstruction var9 = null;

      try {
         for (int var10 = 0; var10 < var1.size(); var10++) {
            var9 = var1.get(var10);
            int var11 = var2.size();
            var8.insn = var9;
            var8.address = var5;
            var8.irAddress = var11;
            var7.clear();
            var8.r = var7;
            this.convertInstruction(var8);

            for (IEStatement var13 : var7) {
               if (var13.getPrimaryLowerLevelAddress() == null) {
                  var13.setLowerLevelAddress(var5);
               }
            }

            var2.addAll(var7);
            var5 += var9.getSize();
         }
      } catch (Throwable var17) {
         logger.error(S.L("Error: Instruction cannot be converted: %s %s"), Formatter.byteArrayToHex(var9.getCode()), var9.format(var5));
         logger.catchingSilent(var17);
         throw var17;
      } finally {
         ;
      }
   }

   public void convertInstruction(ConverterInstructionEntry var1) {
      this.insnConversionCount++;

      try {
         if (!this.attemptConversionByExtension(var1) && !this.convertInstructionFirstChance(var1, var1.insn.getMnemonic(), true)) {
            this.convertInstructionLastChance(var1);
            this.insnConversionCountUntranslated++;
            UnsupportedConversionException var2 = new UnsupportedConversionException("Cannot convert instruction: " + var1.insn);
            if (Licensing.isDebugBuild()) {
               throw var2;
            }

            aeb.q(var2);
         }
      } catch (RuntimeException var4) {
         if (Licensing.isDebugBuild()) {
            throw var4;
         }

         String var3 = Strings.ff("%s @ 0x%X: %s", var1.insn.getMnemonic(), var1.address, Formatter.byteArrayToHex(var1.insn.getCode()));
         JebCoreService.notifySilentExceptionToClient(var4, Maps.toMap("instruction", var3));
         this.insnConversionCountFailure++;
         if (this.missedInsnCounter != null) {
            this.missedInsnCounter.inc(var1.insn.getMnemonic());
         }

         this.convertInstructionLastChance(var1);
      }
   }

   protected boolean convertInstructionFirstChance(ConverterInstructionEntry var1, String var2, boolean var3) {
      throw new RuntimeException("Not implemented");
   }

   protected boolean convertInstructionLastChance(ConverterInstructionEntry var1) {
      throw new RuntimeException("Not implemented");
   }

   protected IEGeneric convertOperand(long var1, IInstruction var3, int var4) {
      throw new RuntimeException("Not implemented");
   }

   protected boolean attemptConversionByExtension(ConverterInstructionEntry var1) {
      return this.decompiler == null ? false : (Boolean)this.decompiler.getExtensionsManager().convertInstruction(this, this.ctx, var1).getResult();
   }

   protected boolean attemptCallInliningByExtension(ConverterInstructionEntry var1, long var2) {
      return this.decompiler == null ? false : (Boolean)this.decompiler.getExtensionsManager().convertToInlinedCall(this, this.ctx, var1, var2).getResult();
   }

   protected void postBlockConversion(CFG var1, BasicBlock var2, List var3, int var4) {
      AddressableInstruction var5 = var2.getBranchingInstruction2();
      if (var5 != null) {
         long var6 = var5.getOffset();
         INativeContinuousItem var8 = this.nctx.getNativeItemAt(var6);
         if (var8 instanceof INativeInstructionItem) {
            InstructionHints var9 = ((INativeInstructionItem)var8).getHints(false);
            if (var9 != null) {
               if (var5.getRoutineCall().isBroken()) {
                  if (var9.isFakeCall()) {
                     this.sanitizeFakeBranch(var6, var8, var3.subList(var3.size() - var4, var3.size()), true);
                  }
               } else if (var5.getBreakingFlow().isBroken() && var9.isActualCall()) {
                  this.sanitizeFakeBranch(var6, var8, var3.subList(var3.size() - var4, var3.size()), false);
               }
            }
         }
      }
   }

   private void sanitizeFakeBranch(long var1, INativeContinuousItem var3, List var4, boolean var5) {
      byte var6;
      byte var7;
      if (var5) {
         var6 = 2;
         var7 = 1;
      } else {
         var6 = 1;
         var7 = 2;
      }

      for (int var8 = 0; var8 < var4.size(); var8++) {
         IEStatement var9 = (IEStatement)var4.get(var8);
         Long var10 = var9.getPrimaryLowerLevelAddress();
         if (var10 != null && var10 == var1 && var9 instanceof alj && ((alj)var9).Dw() == var6) {
            ((alj)var9).q(var7);
         }
      }
   }

   public final List convertBlockForTest(BasicBlock var1) {
      ArrayList var4;
      try {
         aml var2 = new aml((alr)this.gCtx);
         this.setCurrentContext(var2);
         ArrayList var3 = new ArrayList();
         var1 = this.preBlockConversion(null, var1, var3);
         this.convertBlock(var1, var3);
         var2.setStatements(var3);
         this.gCtx.addRoutineContext(var2);
         var4 = var3;
      } finally {
         this.setCurrentContext(null);
      }

      return var4;
   }

   @Override
   public IEMem createStackMemoryAccess(IEGeneric var1, int var2) {
      return this.getGlobalContext().createMem(null, var1, var2);
   }

   @Override
   public long sanitizeNativeAddress(long var1) {
      return this.getAddressBitsize() >= 64 ? var1 : MathUtil.zeroExtend(var1, this.getAddressBitsize());
   }

   @Override
   public Boolean isSegmentEMemReferencingPrimaryMemory(IEMem var1) {
      return true;
   }

   @Override
   public IEPrototypeHandler getPrototypeHandler(IERoutineContext var1) {
      return new EPrototypeHandler(var1);
   }

   @Override
   public boolean canCreateCalls() {
      return true;
   }

   @Override
   public IEGeneric convertReturnLocation(IERoutineContext var1, IWildcardPrototype var2) {
      ICallingConvention var3 = var2.getCallingConvention();
      IEVar var4 = this.getStackPointer();
      IEGlobalContext var5 = this.getGlobalContext();
      StorageEntry var6 = var3.getReturnAddressSlot();
      if (var6 == null) {
         return null;
      } else {
         switch (var6.getType()) {
            case REGISTER:
               return this.getRegisterVariableFromNativeRegisterId(var6.getValue());
            case STACK:
               int var7 = var6.getValueAsStackIndex();
               if (var7 == 0) {
                  return var5.createMem(var4, this.getAddressBitsize());
               }

               int var8 = var7 * this.getAddressBitsize() / 8;
               return var5.createMem(var5.createOperation(OperationType.ADD, var4, var5.createImm(var8, var4.getBitsize())), this.getAddressBitsize());
            default:
               throw new DecompilerException("Cannot convert return location");
         }
      }
   }

   @Override
   public List convertReturnExpressions(IERoutineContext var1, IWildcardPrototype var2, INativeMethodItem var3, List var4, List var5) {
      ICallingConvention var6 = var2.getCallingConvention();
      IEVar var7 = this.getStackPointer();
      IEGlobalContext var8 = this.getGlobalContext();
      ArrayList var9 = new ArrayList();
      ArrayList var10 = new ArrayList(var6.getSpoiledRegisters());
      int var11 = EUtil.determineArgumentStackSlotCount(var2, var4);
      IStorageEntryGenerator var12 = var6.getOutputsGenerator(var11);
      int var13 = 0;

      for (IWildcardType var15 : var2.getReturnTypes()) {
         Object var16 = var1.getConverter().getOutputVariableByIndex(var1, var13);
         if (var16 != null) {
            var13++;
         } else {
            int var17 = var15.getBitsize();
            StorageEntry var18 = var12.next(var15.getLayoutInfo());
            switch (var18.getType()) {
               case REGISTER:
                  long var30 = var18.getValue();
                  var16 = this.getRegisterVariableFromNativeRegisterId(var30);
                  var10.remove(var30);
                  break;
               case STACK:
                  int var29 = var18.getValueAsStackIndex();
                  int var31 = var29 * this.getStackSlotSize();
                  var16 = this.createStackMemoryAccess(EUtil.add(var7, var8.createImm(var31, var7.getBitsize())), var17);
                  break;
               case REGISTER_PAIR:
                  Endianness var19 = this.gCtx.isBigEndian() ? Endianness.BIG_ENDIAN : Endianness.LITTLE_ENDIAN;
                  long var20 = var18.getValue(var19);
                  long var22 = var18.getValue2(var19);
                  IEGeneric var24 = this.getRegisterVariableFromNativeRegisterId(var20);
                  IEGeneric var25 = this.getRegisterVariableFromNativeRegisterId(var22);
                  var16 = this.gCtx.createCompose(var24, var25);
                  var10.remove(var20);
                  var10.remove(var22);
                  break;
               default:
                  throw new DecompilerException("Cannot convert return");
            }

            var13 += var18.getSlotCount();
         }

         ((IEGeneric)var16).setType(var15);
         var9.add(var16);
      }

      if (var5 != null) {
         for (long var27 : var10) {
            IEGeneric var28 = this.getRegisterVariableFromNativeRegisterId(var27);
            if (var28 != null) {
               var5.add(var28);
            }
         }
      }

      return var9;
   }

   @Override
   public List convertParameterExpressions(IERoutineContext var1, IWildcardPrototype var2, INativeMethodItem var3, List var4) {
      ICallingConvention var5 = var2.getCallingConvention();
      IEVar var6 = this.getStackPointer();
      IEGlobalContext var7 = this.getGlobalContext();
      ArrayList var8 = new ArrayList();
      IStorageEntryGenerator var9 = var5.getInputsGenerator();
      int var10 = 0;

      for (IWildcardType var12 : EUtil.gatherArgumentTypes(var2, var4)) {
         IEGeneric var13 = var1.getConverter().getInputVariableByIndex(var1, var10);
         if (var13 != null) {
            var10++;
         } else {
            int var14 = var12.getBitsize();
            StorageEntry var15 = var9.next(var12.getLayoutInfo());
            switch (var15.getType()) {
               case REGISTER:
                  var13 = this.getRegisterVariableFromNativeRegisterId(var15.getValue());
                  var13 = var13.part(var14);
                  break;
               case STACK:
                  int var20 = var15.getValueAsStackIndex();
                  int var21 = var20 * this.getStackSlotSize();
                  var13 = this.createStackMemoryAccess(EUtil.add(var6, var7.createImm(var21, var6.getBitsize())), var14);
                  break;
               case REGISTER_PAIR:
                  Endianness var16 = this.gCtx.isBigEndian() ? Endianness.BIG_ENDIAN : Endianness.LITTLE_ENDIAN;
                  IEGeneric var17 = this.getRegisterVariableFromNativeRegisterId(var15.getValue(var16));
                  IEGeneric var18 = this.getRegisterVariableFromNativeRegisterId(var15.getValue2(var16));
                  var13 = this.gCtx.createCompose(var17, var18);
                  break;
               default:
                  throw new RuntimeException("TBI");
            }

            var10 += var15.getSlotCount();
         }

         var13.setType(var12);
         var8.add(var13);
      }

      return var8;
   }

   @Override
   public IEVar getInputVariableByIndex(IERoutineContext var1, int var2) {
      return null;
   }

   @Override
   public IEVar getOutputVariableByIndex(IERoutineContext var1, int var2) {
      return null;
   }

   @Override
   public Integer determineStackPointerDeltaAfterIRCall(IWildcardPrototype var1, List var2) {
      ICallingConvention var3 = var1.getCallingConvention();
      if (var3 != null && var3.getReturnAddressSlot() != null) {
         int var4 = 0;
         if (var3.getReturnAddressSlot().getType() == StorageEntry.Type.STACK) {
            var4++;
         }

         if (var3.isStackCleanedByCallee()) {
            int var5 = EUtil.determineArgumentStackSlotCount(var1, var2);
            var4 += var5;
            var4 += EUtil.determineReturnValuesStackSlotCount(var1, var5);
         }

         return var4 * this.getStackSlotSize();
      } else {
         return null;
      }
   }

   @Override
   public Integer determineStackBytesUsedByCall(IWildcardPrototype var1, List var2) {
      ICallingConvention var3 = var1.getCallingConvention();
      int var4 = 0;
      if (var3.getReturnAddressSlot().getType() == StorageEntry.Type.STACK) {
         var4++;
      }

      int var5 = EUtil.determineArgumentStackSlotCount(var1, var2);
      var4 += var5;
      var4 += EUtil.determineReturnValuesStackSlotCount(var1, var5);
      return var4 * this.getStackSlotSize();
   }

   @Override
   public Integer determineStackPointerDeltaFromSimulation(SimulationPointInformation var1) {
      return null;
   }

   @Override
   public IEBranchDetails getDefaultBranchToRoutineSideEffects(INativeMethodItem var1) {
      return new EBranchDetails();
   }

   @Override
   public IWildcardPrototype buildFailsafePrototype(IERoutineContext var1, IEStatement var2) {
      IWildcardTypeManager var3 = var1.getWildcardTypeManager();
      IPrototypeItem var4 = var3.getNativeTypeManager().createPrototype(null, null, null, null);
      return var3.createPrototype(var4);
   }

   private IEGeneric findReturnAddrVar(IERoutineContext var1, CFG var2, BasicBlock var3, IEGeneric var4, StorageEntry var5, Collection var6) {
      Object var7;
      switch (var5.getType()) {
         case REGISTER:
            IDFA var10 = var2.doDataFlowAnalysis();
            var7 = var1.getInputVariableForRegister(var10, var5.getValue());
            if (var7 == null) {
               IEGeneric var9 = this.getRegisterVariableFromNativeRegisterId(var5.getValue());
               var7 = var1.retrieveVariableForRegister(var9, var6, false);
            }
            break;
         case STACK:
            int var8 = var5.getValueAsStackIndex();
            var7 = var1.getStackManager().getVariableAtSlot(var8);
            break;
         default:
            throw new RuntimeException("Unsupported entry type for location of return address: " + var5);
      }

      return (IEGeneric)var7;
   }

   @Override
   public int insertReturns(IERoutineContext var1) {
      IWildcardPrototype var2 = var1.getPrototype();
      if (var2 == null) {
         throw new IllegalStateException("Prototype discovery must be performed first");
      } else {
         ICallingConvention var3 = var2.getCallingConvention();
         Object[] var10000 = new Object[]{var3};
         CFG var4 = var1.getCfg();
         IDFA var5 = var4.doDataFlowAnalysis();
         int var6 = 0;
         int var7 = 0;

         label120:
         while (true) {
            BasicBlock var8;
            AddressableInstruction var9;
            IEStatement var10;
            boolean var11;
            int var12;
            Collection var13;
            StorageEntry var14;
            IEGeneric var16;
            while (true) {
               while (true) {
                  if (var7 >= var4.size()) {
                     if (var6 != 0) {
                        var4.invalidateDataFlowAnalysis();
                     }

                     return var6;
                  }

                  var8 = var4.get(var7++);
                  var9 = var8.getLast2();
                  var10 = (IEStatement)var9.getInstruction();
                  if (var10 instanceof IECall || EUtil.isPCAssign(var10)) {
                     var11 = false;
                     if (var8.outsize() == 0) {
                        break;
                     }

                     if (var10.isCall() && Boolean.FALSE.equals(EUtil.checkCallReturnAddress(var1, var4, var8, var8.size() - 1))) {
                        var11 = true;
                        break;
                     }
                  }
               }

               var12 = EUtil.determineArgumentStackSlotCount(var2, null);
               var13 = var5.getOutputs(var8);
               var14 = var3.getReturnAddressSlot(var12);
               if (var10 instanceof IECall var26) {
                  if (!Booleans.isTrue(var26.getNonReturning())) {
                     var16 = var26.getReturnLocation();
                     break;
                  }
               } else if (var10 instanceof IEAssign var17 && var17.getLeftOperand() == this.getProgramCounter() && var9.getBreakingFlow().isBroken()) {
                  var16 = var17.getSrcOperand();
                  break;
               }
            }

            IEGeneric var15 = this.findReturnAddrVar(var1, var4, var8, var16, var14, var13);
            if (var15 != null) {
               var15 = this.normalizeBranchingExpression(var5, var8, var16, var15);
               if (var15 != null) {
                  logger.iH("Will insert a return in instruction: '%s'", var10);
                  IStorageEntryGenerator var27 = var3.getOutputsGenerator(var12);
                  ArrayList var18 = new ArrayList();
                  Object var19 = null;

                  for (IWildcardType var21 : var2.getReturnTypes()) {
                     var14 = var27.next(var21.getLayoutInfo());
                     if (var14 == null) {
                        logger.warn("Cannot find storage location for output: %s", var21.getLayoutInfo());
                     } else {
                        switch (var14.getType()) {
                           case REGISTER:
                              EVarCopyFinder var31 = var1.copyFinder(var14, var13, var9.getOffset());
                              var19 = var31.getIRForSlicedReg(true);
                              if (var19 == null) {
                                 logger.debug("Retval in register is missing");
                              }
                              break;
                           case STACK:
                              int var30 = var14.getValueAsStackIndex();
                              var19 = var1.getStackManager().getVariableAtSlot(var30);
                              if (var19 == null) {
                                 logger.debug("Retval on stack is missing");
                              }
                              break;
                           case REGISTER_PAIR:
                              EVarCopyFinder var22 = var1.copyFinder(var14, var13, var9.getOffset());
                              var19 = var22.getVarForRegPair(true);
                              if (var19 == null) {
                                 logger.debug("Retval in register-pair is missing");
                              }
                        }

                        if (var19 == null) {
                           continue label120;
                        }

                        var18.add(var19);
                     }
                  }

                  IEReturn var28 = var1.createReturn(var18);
                  if (var10 instanceof IECall) {
                     if (var10.getSize() <= 1) {
                        logger.warn("Cannot insert EReturn (ECall cannot be split)");
                        continue;
                     }

                     if (var11) {
                        var4.deleteOutEdges(var8);
                     }

                     long var29 = var8.getEndAddress() - 1L;
                     var10.adjustSize(-1);
                     ((IECall)var10).setTentativeCall(false);
                     ((aln)var10).setReturnLocation(null);
                     BasicBlock var23 = new BasicBlock(var29);
                     var23.add(var28);
                     var28.copyLowerLevelAddresses(var10);
                     var4.addBlock(var4.indexOf(var8) + 1, var23);
                     var7++;
                     var4.addEdge(var8, var23, 0);
                  } else {
                     var28.copyProperties(var10);
                     var8.set(var8.size() - 1, var28);
                  }

                  var6++;
               }
            }
         }
      }
   }

   @Override
   public IEGeneric normalizeBranchingExpression(IDFA var1, BasicBlock var2, IEGeneric var3, IEGeneric var4) {
      if (var3 == null) {
         return null;
      } else if (var4 == null) {
         return var3;
      } else {
         return !this.isPCRightValueCompatibleReturnValue(var1, var2, var3, var4) ? null : var3;
      }
   }

   protected final boolean isPCRightValueCompatibleReturnValue(IDFA var1, BasicBlock var2, IEGeneric var3, IEGeneric var4) {
      if (var3 == var4) {
         return true;
      } else {
         if (var3 instanceof IEVar var5) {
            long var6 = var2.getLastAddress();

            while (true) {
               Collection var8 = var1.getUseDefs(var6, var5.getId());
               long var9 = -1L;
               IEStatement var11 = null;

               for (long var13 : var8) {
                  IEStatement var15 = (IEStatement)var1.getCfg().getInstruction(var13);
                  if (var15 instanceof IEAssign && !(((IEAssign)var15).getDstOperand() instanceof IEMem)) {
                     if (var11 != null) {
                        var11 = null;
                        break;
                     }

                     var11 = var15;
                     var9 = var13;
                  }
               }

               if (var11 == null || !(((IEAssign)var11).getSrcOperand() instanceof IEVar)) {
                  break;
               }

               IEVar var16 = (IEVar)((IEAssign)var11).getSrcOperand();
               if (var16.equalsEx(var4, false)) {
                  return true;
               }

               var5 = var16;
               var6 = var9;
            }
         }

         return false;
      }
   }

   @Override
   public int defaultPCConversion(IERoutineContext var1) {
      CFG var2 = var1.getCfg();
      int var3 = 0;

      for (BasicBlock var5 : var2.getBlocks()) {
         AddressableInstruction var6 = var5.getLast2();
         if (var6.getInstruction() instanceof IEAssign) {
            IEAssign var7 = (IEAssign)var6.getInstruction();
            if (var7.getLeftOperand() == this.getProgramCounter()) {
               IFlowInformation var8 = var7.getBreakingFlow(var6.getOffset(), true);
               if (var8.isBroken()) {
                  Object var9 = null;
                  IEGeneric var10 = var7.getRightOperand();
                  if (var10 instanceof IECond) {
                     IEGeneric var11 = ((IECond)var10).getCondition();
                     IEGeneric var12 = ((IECond)var10).getExpressionTrue();
                     IEGeneric var13 = ((IECond)var10).getExpressionFalse();
                     long var14 = var6.getOffset() + var7.getSize();
                     Long var16 = null;
                     Long var17 = null;
                     if (EUtil.isLikeLongImmediate(var12)) {
                        var16 = var1.convertNativeAddress(EUtil.evaluateAddress_preVerified(var12));
                     }

                     if (EUtil.isLikeLongImmediate(var13)) {
                        var17 = var1.convertNativeAddress(EUtil.evaluateAddress_preVerified(var13));
                     }

                     if (var16 != null && var16 == var14) {
                        var9 = var1.createOperation(OperationType.LOG_NOT, var11);
                        var10 = var13;
                     } else if (var17 != null && var17 == var14) {
                        var9 = var11;
                        var10 = var12;
                     }
                  }

                  IEJumpFar var18 = var1.createJumpFar(var10, (IEGeneric)var9);
                  var18.setPossibleTargets(var8.getTargets());
                  var18.copyProperties(var7);
                  var5.set(var5.size() - 1, var18);
                  var3++;
               }
            }
         }
      }

      if (var3 != 0) {
         var2.invalidateDataFlowAnalysis();
      }

      return var3;
   }

   @Override
   public boolean resolveCustomCalls(IERoutineContext var1) {
      return false;
   }

   @Override
   public final void initializeStateRegisters(EState var1, Long var2) {
      for (IEVar var4 : this.getGlobalContext().getAllRegisters()) {
         try {
            long var5 = this.getNativeRegisterIdFromRegisterVariable(var4);
            if (!this.isPossibleParameterRegisterForProcessorCallingConventions(var5)) {
               var1.setValue(var4, 0L);
            }
         } catch (DecompilerException var7) {
            var1.setValue(var4, 0L);
         }
      }

      if (var2 != null) {
         var1.setValue(this.getProgramCounter(), var2);
      }

      this.customInitStateRegisters(var1, var2);
   }

   @Override
   public void customInitStateRegisters(EState var1, Long var2) {
   }

   private void initParameterRegistersForProcessorCallingConventions() {
      this.parameterRegistersForAllCC = Collections.emptySet();
      ITypeManager var1 = this.nctx.getTypeManager();
      if (var1 != null) {
         ICallingConventionManager var2 = var1.getCallingConventionManager();
         if (var2 != null) {
            this.parameterRegistersForAllCC = CallingConventionUtil.getParameterRegisters(
               var2, this.gCtx.isBigEndian() ? Endianness.BIG_ENDIAN : Endianness.LITTLE_ENDIAN
            );
         }
      }
   }

   public boolean isPossibleParameterRegisterForProcessorCallingConventions(long var1) {
      if (this.parameterRegistersForAllCC == null) {
         this.initParameterRegistersForProcessorCallingConventions();
      }

      return this.parameterRegistersForAllCC.contains(var1);
   }

   private void initSpoiledRegistersForProcessorCallingConventions() {
      this.spoiledRegistersForAllCC = Collections.emptySet();
      ITypeManager var1 = this.nctx.getTypeManager();
      if (var1 != null) {
         ICallingConventionManager var2 = var1.getCallingConventionManager();
         if (var2 != null) {
            this.spoiledRegistersForAllCC = CallingConventionUtil.getSpoiledRegisters(var2);
         }
      }
   }

   public boolean isPossibleSpoiledRegistersForProcessorCallingConventions(long var1) {
      if (this.spoiledRegistersForAllCC == null) {
         this.initSpoiledRegistersForProcessorCallingConventions();
      }

      return this.spoiledRegistersForAllCC.contains(var1);
   }

   @Override
   public int getStateProcessorMode(EState var1) {
      return this.proc.getMode();
   }

   @Override
   public IEVar getReturnAddressRegister() {
      return null;
   }

   @Override
   public IEVar getGPRegister(int var1) {
      return null;
   }

   @Override
   public IEVar getFPRegister(int var1) {
      return null;
   }

   @Override
   public IEVar getTempRegister(int var1) {
      return null;
   }

   @Override
   public String formatStatistics() {
      StringBuilder var1 = new StringBuilder();
      Strings.ff(var1, "Method conversion count: %d success, %d failure", this.methodConversionCountSuccess, this.methodConversionCountFailure);
      int var2 = this.methodConversionCountFailure + this.methodConversionCountSuccess;
      if (var2 > 0) {
         double var3 = (double)this.methodConversionCountSuccess / var2 * 100.0;
         Strings.ff(var1, " (success rate: %.1f%%)", var3);
      }

      return var1.toString();
   }

   @Override
   public ICStatement generateASTForUntranslatedIR(IEUntranslatedInstruction var1, IERoutineContext var2, ICMethod var3) {
      return null;
   }

   @Override
   public IEImm evaluateUntranslatedIR(IEUntranslatedInstruction var1, IERoutineContext var2, EState var3) {
      return null;
   }

   public final boolean autoConvert(ConverterInstructionEntry var1, ACS var2) {
      return new AbstractConverter.AutoConverter(var2).convert(var1);
   }

   @SerDisabled
   private class AutoConverter {
      ACS acs;
      List _opnds;

      AutoConverter(ACS var2) {
         this.acs = var2;
      }

      boolean convert(ConverterInstructionEntry var1) {
         ACS.OPS var2 = this.acs.getOpcodeSemantic();
         this._opnds = new ArrayList(var1.insn.getOperands().length);

         for (int var3 = 0; var3 < var1.insn.getCountOfOperands(); var3++) {
            this._opnds.add(AbstractConverter.this.convertOperand(var1.address, var1.insn, var3));
         }

         IEGeneric var10 = this.findDst();
         IEGeneric var4 = this.findSrc1();
         IEGeneric var5 = this.findSrc2();
         IEGeneric var6 = this.findSrc3();
         OperationType var7 = var2.getDirectConversionOperationType();
         if (var7 != null) {
            Integer var24 = this.acs.operationBitsize();
            if (var24 == null) {
               this.asg(var1, var10, AbstractConverter.this.gCtx.createOperation(var7, var4, var5));
            } else {
               Object var26 = AbstractConverter.this.gCtx.createOperation(var7, var4.part(var24), var5 == null ? null : var5.part(var24));
               if (this.acs.extensionMode() == null) {
                  var10 = var10.part(var24);
               } else if (this.acs.extensionMode() == 0) {
                  var26 = ((IEGeneric)var26).zeroExtend(var10.getBitsize());
               } else if (this.acs.extensionMode() == 1) {
                  var26 = ((IEGeneric)var26).signExtend(var10.getBitsize());
               }

               this.asg(var1, var10, (IEGeneric)var26);
            }

            return true;
         } else {
            switch (var2) {
               case SET_IF_EQ:
               case SET_IF_NE:
               case SET_IF_LT_S:
               case SET_IF_LT_U:
               case SET_IF_LE_S:
               case SET_IF_LE_U:
               case SET_IF_GT_S:
               case SET_IF_GT_U:
               case SET_IF_GE_S:
               case SET_IF_GE_U:
                  var7 = switch (var2) {
                     case SET_IF_EQ -> OperationType.LOG_EQ;
                     case SET_IF_NE -> OperationType.LOG_NEQ;
                     case SET_IF_LT_S -> OperationType.LT_S;
                     case SET_IF_LT_U -> OperationType.LT_U;
                     case SET_IF_LE_S -> OperationType.LE_S;
                     case SET_IF_LE_U -> OperationType.LE_U;
                     case SET_IF_GT_S -> OperationType.GT_S;
                     case SET_IF_GT_U -> OperationType.GT_U;
                     case SET_IF_GE_S -> OperationType.GE_S;
                     case SET_IF_GE_U -> OperationType.GE_U;
                     default -> throw new RuntimeException();
                  };
                  int var23 = var10.getBitsize();
                  this.asg(var1, var10, AbstractConverter.this.gCtx.createCond(EUtil.op(var7, var4, var5), EUtil.one(var23), EUtil.zero(var23)));
                  break;
               case JUMP_IF_EQ:
               case JUMP_IF_NE:
               case JUMP_IF_LT_S:
               case JUMP_IF_LT_U:
               case JUMP_IF_LE_S:
               case JUMP_IF_LE_U:
               case JUMP_IF_GT_S:
               case JUMP_IF_GT_U:
               case JUMP_IF_GE_S:
               case JUMP_IF_GE_U:
                  var7 = switch (var2) {
                     case JUMP_IF_EQ -> OperationType.LOG_EQ;
                     case JUMP_IF_NE -> OperationType.LOG_NEQ;
                     case JUMP_IF_LT_S -> OperationType.LT_S;
                     case JUMP_IF_LT_U -> OperationType.LT_U;
                     case JUMP_IF_LE_S -> OperationType.LE_S;
                     case JUMP_IF_LE_U -> OperationType.LE_U;
                     case JUMP_IF_GT_S -> OperationType.GT_S;
                     case JUMP_IF_GT_U -> OperationType.GT_U;
                     case JUMP_IF_GE_S -> OperationType.GE_S;
                     case JUMP_IF_GE_U -> OperationType.GE_U;
                     default -> throw new RuntimeException();
                  };
                  IEImm var22 = EUtil.imm(var1.address + var1.insn.getSize(), AbstractConverter.this.getAddressBitsize());
                  IECond var25 = AbstractConverter.this.gCtx.createCond(EUtil.op(var7, var4, var5), var6, var22);
                  var1.r.add(AbstractConverter.this.ctx.createBranchAssign(AbstractConverter.this.getProgramCounter(), var25, false));
                  break;
               case NOP:
                  var1.r.add(AbstractConverter.this.ctx.createNop());
                  break;
               case MOVE:
                  this.asg(var1, var10, var4);
                  break;
               case STORE:
                  Integer var14 = this.acs.operationBitsize();
                  if (var14 == null) {
                     var14 = this.findSrc1().getBitsize();
                  }

                  if (var10 != null) {
                     Assert.a(var10 instanceof IEMem);
                     Assert.a(var10.getBitsize() == var14);
                     this.asg(var1, var10, var4.part(var14));
                  } else if (var6 == null) {
                     this.asg(var1, AbstractConverter.this.gCtx.createMem(var5, var14), var4.part(var14));
                  } else {
                     IEOperation var21 = AbstractConverter.this.gCtx.createOperation(OperationType.ADD, var5, var6);
                     this.asg(var1, AbstractConverter.this.gCtx.createMem(var21, var14), var4.part(var14));
                  }
                  break;
               case LOAD:
                  Object var12;
                  if (var4 instanceof IEMem) {
                     var12 = var4;
                  } else if (var5 == null) {
                     Integer var18 = this.acs.operationBitsize();
                     var12 = AbstractConverter.this.gCtx.createMem(var4, var18);
                  } else {
                     Integer var19 = this.acs.operationBitsize();
                     var12 = AbstractConverter.this.gCtx.createMem(AbstractConverter.this.gCtx.createOperation(OperationType.ADD, var4, var5), var19);
                  }

                  boolean var20 = this.acs.isSignedExtension(true);
                  var12 = var20 ? var12.signExtend(var10.getBitsize()) : var12.zeroExtend(var10.getBitsize());
                  this.asg(var1, var10, var12);
                  break;
               case JUMP:
                  Assert.a(var4 != null);
                  IEVar var11 = null;
                  if (var10 != null) {
                     if (var10.equals(var4)) {
                        var11 = AbstractConverter.this.getTempRegister(0);
                        this.asg(var1, var11, var10);
                     }

                     IEImm var8 = EUtil.imm(var1.address + var1.insn.getSize(), AbstractConverter.this.getAddressBitsize());
                     this.asg(var1, var10, var8);
                  }

                  Object var17 = var11 != null ? var11 : var4;
                  if (var5 != null) {
                     var17 = EUtil.add((IEGeneric)var17, var5);
                  }

                  if (this.acs.maskOnSource() != null) {
                     IEImm var9 = EUtil.imm(BigInteger.valueOf(this.acs.maskOnSource()), ((IEGeneric)var17).getBitsize());
                     var17 = EUtil.andB((IEGeneric)var17, var9);
                  }

                  var1.r.add(AbstractConverter.this.ctx.createBranchAssign(AbstractConverter.this.getProgramCounter(), (IEGeneric)var17, var10 != null));
                  break;
               default:
                  throw new RuntimeException("TBI: AUTOCONV: " + var2);
            }

            return true;
         }
      }

      void asg(ConverterInstructionEntry var1, IEGeneric var2, IEGeneric var3) {
         var1.r.add(AbstractConverter.this.ctx.createAssign(var2, var3));
      }

      IEGeneric findSrc1() {
         return this.find(this._opnds, 2);
      }

      IEGeneric findSrc2() {
         return this.find(this._opnds, 4);
      }

      IEGeneric findSrc3() {
         return this.find(this._opnds, 8);
      }

      IEGeneric findDst() {
         return this.find(this._opnds, 1);
      }

      private IEGeneric find(List var1, int var2) {
         int var3 = this.acs.findOperandIndexByFlag(var2);
         if (var3 < 0) {
            return null;
         } else if (var3 < var1.size()) {
            return (IEGeneric)var1.get(var3);
         } else {
            int var4 = this.acs.getOperandSemanticFlags(var3);
            if ((var4 & 0xFF) != 0) {
               if ((var4 & 65536) == 65536) {
                  return EUtil.zero(AbstractConverter.this.getRegisterBitsize());
               }

               if ((var4 & 251658240) != 0) {
                  return AbstractConverter.this.getGPRegister((var4 >> 24) - 1);
               }
            }

            return null;
         }
      }
   }
}
