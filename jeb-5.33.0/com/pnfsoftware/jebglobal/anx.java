package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.code.DecompilationContext;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.IClassRebuilder;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IDecompiledMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeDecompilerUnit;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.NativeDecompilationStage;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECall;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.items.IMethodManager;
import com.pnfsoftware.jeb.core.units.code.asm.items.IMethodTable;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeClassItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodDataItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.code.asm.type.ICallingConvention;
import com.pnfsoftware.jeb.core.units.code.asm.type.IClassManager;
import com.pnfsoftware.jeb.core.units.code.asm.type.IClassType;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IPackageManager;
import com.pnfsoftware.jeb.core.units.code.asm.type.IPrototypeItem;
import com.pnfsoftware.jeb.core.units.code.asm.type.IReferenceType;
import com.pnfsoftware.jeb.core.units.code.asm.type.ITypeManager;
import com.pnfsoftware.jeb.core.units.code.asm.type.IVirtualTableDefinition;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.collect.MultiMap;
import com.pnfsoftware.jeb.util.concurrent.ACLock;
import com.pnfsoftware.jeb.util.concurrent.OperationTimedOutException;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class anx implements IClassRebuilder {
   protected static final ILogger pC = GlobalLog.getLogger(anx.class);
   protected INativeCodeUnit A;
   protected INativeDecompilerUnit kS;
   protected int wS;
   protected int UT;
   protected ITypeManager E;
   protected IPackageManager sY;
   protected IMethodManager ys;
   protected IClassManager ld;
   private List gp;
   private Map oT;
   private Map fI = new HashMap();
   private TreeMap WR = new TreeMap();
   private anu NS = new anu();
   private List vP = new ArrayList();
   private List xC = new ArrayList();
   private List ED = new ArrayList();

   public anx(INativeCodeUnit var1, INativeDecompilerUnit var2) {
      this.A = var1;
      this.kS = var2;
      this.wS = var1.getMemory().getSpaceBits() / 8;
      this.UT = this.wS;
      this.E = var1.getTypeManager();
      this.sY = var1.getPackageManager();
      this.ys = var1;
      this.ld = var1.getClassManager();
   }

   private List A() {
      ArrayList var1 = new ArrayList();

      for (INativeMethodItem var3 : this.A.getInternalMethodsLeafFirst()) {
         var1.add(var3.getData());
      }

      return var1;
   }

   @Override
   public final boolean quickDetermination() {
      int var1 = 0;

      boolean var3;
      try (ACLock var2 = this.A.getLock().a()) {
         if (this.oT == null) {
            this.oT = new LinkedHashMap();
            if (!this.pC()) {
               return false;
            }

            if (this.gp == null) {
               this.gp = this.A();
            }

            for (INativeMethodDataItem var5 : this.gp) {
               long var6 = var5.getMemoryAddress();
               INativeMethodItem var8 = var5.getRoutine();
               if (var8 != null && var8.getClassItem() == null) {
                  try {
                     if (this.pC(var6, var8)) {
                        this.oT.put(var6, var8);
                     }
                  } catch (Exception var11) {
                     if (++var1 > 5) {
                        throw var11;
                     }
                  }
               }
            }
         }

         var3 = !this.oT.isEmpty();
      }

      return var3;
   }

   protected boolean pC() {
      return true;
   }

   @Override
   public int process() {
      try (ACLock var1 = this.A.getLock().a()) {
         if (!this.quickDetermination()) {
            return 0;
         } else {
            Object[] var10000 = new Object[]{this.oT.size()};
            if (!this.kS()) {
               return 0;
            } else if (!this.wS()) {
               return 0;
            } else if (!this.UT()) {
               return 0;
            } else {
               this.E();
               this.sY();
               this.ys();
               return this.NS.pC();
            }
         }
      } catch (Exception var11) {
         pC.catching(var11);
         return -1;
      } finally {
         this.kS.removeFreeElements();
      }
   }

   @Override
   public List getRebuiltClassItems() {
      return this.xC;
   }

   @Override
   public List getGeneratedMethodReferences() {
      return this.ED;
   }

   protected boolean pC(long param1, INativeMethodItem param3) {
      // $VF: Couldn't be decompiled
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      //
      // Bytecode:
      // 000: aload 0
      // 001: aload 3
      // 002: getstatic com/pnfsoftware/jeb/core/units/code/asm/decompiler/NativeDecompilationStage.SIMULATION Lcom/pnfsoftware/jeb/core/units/code/asm/decompiler/NativeDecompilationStage;
      // 005: invokevirtual com/pnfsoftware/jebglobal/anx.pC (Lcom/pnfsoftware/jeb/core/units/code/asm/items/INativeMethodItem;Lcom/pnfsoftware/jeb/core/units/code/asm/decompiler/NativeDecompilationStage;)Lcom/pnfsoftware/jeb/core/units/code/asm/decompiler/IDecompiledMethod;
      // 008: astore 4
      // 00a: aload 4
      // 00c: ifnull 019
      // 00f: aload 4
      // 011: invokeinterface com/pnfsoftware/jeb/core/units/code/asm/decompiler/IDecompiledMethod.isFailure ()Z 1
      // 016: ifeq 029
      // 019: bipush 0
      // 01a: istore 5
      // 01c: aload 0
      // 01d: getfield com/pnfsoftware/jebglobal/anx.kS Lcom/pnfsoftware/jeb/core/units/code/asm/decompiler/INativeDecompilerUnit;
      // 020: aload 3
      // 021: invokeinterface com/pnfsoftware/jeb/core/units/code/asm/decompiler/INativeDecompilerUnit.removeDecompilation (Lcom/pnfsoftware/jeb/core/units/code/asm/items/INativeItem;)V 2
      // 026: iload 5
      // 028: ireturn
      // 029: aload 3
      // 02a: invokeinterface com/pnfsoftware/jeb/core/units/code/asm/items/INativeMethodItem.getData ()Lcom/pnfsoftware/jeb/core/units/code/asm/items/INativeMethodDataItem; 1
      // 02f: ifnonnull 042
      // 032: bipush 0
      // 033: istore 5
      // 035: aload 0
      // 036: getfield com/pnfsoftware/jebglobal/anx.kS Lcom/pnfsoftware/jeb/core/units/code/asm/decompiler/INativeDecompilerUnit;
      // 039: aload 3
      // 03a: invokeinterface com/pnfsoftware/jeb/core/units/code/asm/decompiler/INativeDecompilerUnit.removeDecompilation (Lcom/pnfsoftware/jeb/core/units/code/asm/items/INativeItem;)V 2
      // 03f: iload 5
      // 041: ireturn
      // 042: aload 3
      // 043: invokeinterface com/pnfsoftware/jeb/core/units/code/asm/items/INativeMethodItem.getData ()Lcom/pnfsoftware/jeb/core/units/code/asm/items/INativeMethodDataItem; 1
      // 048: invokeinterface com/pnfsoftware/jeb/core/units/code/asm/items/INativeMethodDataItem.getCFG ()Lcom/pnfsoftware/jeb/core/units/code/asm/cfg/CFG; 1
      // 04d: astore 5
      // 04f: bipush 0
      // 050: istore 6
      // 052: aload 5
      // 054: invokevirtual com/pnfsoftware/jeb/core/units/code/asm/cfg/CFG.addressableInstructions ()Ljava/lang/Iterable;
      // 057: invokeinterface java/lang/Iterable.iterator ()Ljava/util/Iterator; 1
      // 05c: astore 7
      // 05e: aload 7
      // 060: invokeinterface java/util/Iterator.hasNext ()Z 1
      // 065: ifeq 108
      // 068: aload 7
      // 06a: invokeinterface java/util/Iterator.next ()Ljava/lang/Object; 1
      // 06f: checkcast com/pnfsoftware/jeb/core/units/code/AddressableInstruction
      // 072: astore 8
      // 074: iload 6
      // 076: iinc 6 1
      // 079: bipush 80
      // 07b: if_icmplt 081
      // 07e: goto 108
      // 081: aload 8
      // 083: invokevirtual com/pnfsoftware/jeb/core/units/code/AddressableInstruction.getOffset ()J
      // 086: lstore 9
      // 088: aload 0
      // 089: getfield com/pnfsoftware/jebglobal/anx.A Lcom/pnfsoftware/jeb/core/units/INativeCodeUnit;
      // 08c: invokeinterface com/pnfsoftware/jeb/core/units/INativeCodeUnit.getCodeModel ()Lcom/pnfsoftware/jeb/core/units/code/asm/analyzer/INativeCodeModel; 1
      // 091: lload 9
      // 093: invokeinterface com/pnfsoftware/jeb/core/units/code/asm/analyzer/INativeCodeModel.getRegisterValueResolution (J)Lcom/pnfsoftware/jeb/core/units/code/asm/analyzer/IRegistersResolution; 3
      // 098: astore 11
      // 09a: aload 11
      // 09c: ifnull 105
      // 09f: aload 11
      // 0a1: bipush 0
      // 0a2: invokeinterface com/pnfsoftware/jeb/core/units/code/asm/analyzer/IRegistersResolution.getAll (Z)Ljava/util/Map; 2
      // 0a7: astore 12
      // 0a9: aload 12
      // 0ab: invokeinterface java/util/Map.values ()Ljava/util/Collection; 1
      // 0b0: invokeinterface java/util/Collection.iterator ()Ljava/util/Iterator; 1
      // 0b5: astore 13
      // 0b7: aload 13
      // 0b9: invokeinterface java/util/Iterator.hasNext ()Z 1
      // 0be: ifeq 105
      // 0c1: aload 13
      // 0c3: invokeinterface java/util/Iterator.next ()Ljava/lang/Object; 1
      // 0c8: checkcast java/util/List
      // 0cb: astore 14
      // 0cd: aload 14
      // 0cf: invokeinterface java/util/List.size ()I 1
      // 0d4: bipush 1
      // 0d5: if_icmpne 102
      // 0d8: aload 14
      // 0da: bipush 0
      // 0db: invokeinterface java/util/List.get (I)Ljava/lang/Object; 2
      // 0e0: checkcast java/lang/Long
      // 0e3: invokevirtual java/lang/Long.longValue ()J
      // 0e6: lstore 15
      // 0e8: aload 0
      // 0e9: lload 15
      // 0eb: bipush 1
      // 0ec: invokevirtual com/pnfsoftware/jebglobal/anx.pC (JZ)Ljava/lang/Long;
      // 0ef: ifnull 102
      // 0f2: bipush 1
      // 0f3: istore 17
      // 0f5: aload 0
      // 0f6: getfield com/pnfsoftware/jebglobal/anx.kS Lcom/pnfsoftware/jeb/core/units/code/asm/decompiler/INativeDecompilerUnit;
      // 0f9: aload 3
      // 0fa: invokeinterface com/pnfsoftware/jeb/core/units/code/asm/decompiler/INativeDecompilerUnit.removeDecompilation (Lcom/pnfsoftware/jeb/core/units/code/asm/items/INativeItem;)V 2
      // 0ff: iload 17
      // 101: ireturn
      // 102: goto 0b7
      // 105: goto 05e
      // 108: bipush 0
      // 109: istore 7
      // 10b: aload 0
      // 10c: getfield com/pnfsoftware/jebglobal/anx.kS Lcom/pnfsoftware/jeb/core/units/code/asm/decompiler/INativeDecompilerUnit;
      // 10f: aload 3
      // 110: invokeinterface com/pnfsoftware/jeb/core/units/code/asm/decompiler/INativeDecompilerUnit.removeDecompilation (Lcom/pnfsoftware/jeb/core/units/code/asm/items/INativeItem;)V 2
      // 115: iload 7
      // 117: ireturn
      // 118: pop
      // 119: bipush 0
      // 11a: istore 5
      // 11c: aload 0
      // 11d: getfield com/pnfsoftware/jebglobal/anx.kS Lcom/pnfsoftware/jeb/core/units/code/asm/decompiler/INativeDecompilerUnit;
      // 120: aload 3
      // 121: invokeinterface com/pnfsoftware/jeb/core/units/code/asm/decompiler/INativeDecompilerUnit.removeDecompilation (Lcom/pnfsoftware/jeb/core/units/code/asm/items/INativeItem;)V 2
      // 126: iload 5
      // 128: ireturn
      // 129: astore 18
      // 12b: aload 0
      // 12c: getfield com/pnfsoftware/jebglobal/anx.kS Lcom/pnfsoftware/jeb/core/units/code/asm/decompiler/INativeDecompilerUnit;
      // 12f: aload 3
      // 130: invokeinterface com/pnfsoftware/jeb/core/units/code/asm/decompiler/INativeDecompilerUnit.removeDecompilation (Lcom/pnfsoftware/jeb/core/units/code/asm/items/INativeItem;)V 2
      // 135: aload 18
      // 137: athrow
   }

   protected final IDecompiledMethod pC(INativeMethodItem var1, NativeDecompilationStage var2) {
      try {
         DecompilationContext var3 = new DecompilationContext(64, 5000L, null);
         return this.kS.decompileMethodEx(var1, var3, var2);
      } catch (Exception var8) {
         pC.catchingSilent(var8);
         return null;
      } finally {
         ;
      }
   }

   private boolean kS() {
      for (long var2 : this.oT.keySet()) {
         this.A(var2, (INativeMethodItem)this.oT.get(var2));
      }

      return !this.fI.isEmpty();
   }

   // $VF: Could not verify finally blocks. A semaphore variable has been added to preserve control flow.
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   private boolean A(long var1, INativeMethodItem var3) {
      boolean var13 = false /* VF: Semaphore variable */;

      boolean var17;
      label81: {
         boolean var18;
         label80: {
            boolean var9;
            label79: {
               label78: {
                  try {
                     var13 = true;
                     IDecompiledMethod var4 = this.pC(var3, null);
                     if (var4 == null || var4.isFailure()) {
                        var17 = false;
                        var13 = false;
                        break label81;
                     }

                     CFG var16 = var4.getIRContext().getCfg();
                     List var6 = var4.getIRContext().getRoutineInputVariables();
                     if (var6 == null || var6.size() == 0) {
                        var18 = false;
                        var13 = false;
                        break label80;
                     }

                     IEVar var7 = (IEVar)var6.get(0);
                     if (var7 != null) {
                        anw var8 = new anw(var1, var3);
                        if (this.pC(var16, var7, var8)) {
                           this.fI.put(var1, var8);
                           var9 = true;
                           var13 = false;
                           break label79;
                        }

                        var13 = false;
                     } else {
                        var13 = false;
                     }
                     break label78;
                  } catch (OperationTimedOutException var14) {
                     var13 = false;
                  } finally {
                     if (var13) {
                        this.kS.removeDecompilation(var3);
                     }
                  }

                  var17 = false;
                  this.kS.removeDecompilation(var3);
                  return var17;
               }

               boolean var19 = false;
               this.kS.removeDecompilation(var3);
               return var19;
            }

            this.kS.removeDecompilation(var3);
            return var9;
         }

         this.kS.removeDecompilation(var3);
         return var18;
      }

      this.kS.removeDecompilation(var3);
      return var17;
   }

   private boolean pC(CFG var1, IEVar var2, anw var3) {
      HashMap var4 = new HashMap();
      var4.put(var2, 0);
      boolean var5 = false;

      for (IEStatement var7 : var1.instructions()) {
         if (var7 instanceof IECall var15) {
            if (var15.getCountOfArguments() >= 1) {
               IEGeneric var16 = var15.getArgument(0);
               Integer var17 = this.pC(var16, var4);
               if (var17 != null) {
                  INativeMethodItem var21 = var15.getStaticCallsite();
                  if (var21 != null && var21.getData() != null) {
                     long var23 = var21.getData().getMemoryAddress();
                     var3.kS.add(new ans(var17, var23));
                  }
               }
            }
         } else if (var7 instanceof IEAssign var8) {
            IEGeneric var9 = var8.getSrcOperand();
            IEGeneric var10 = var8.getDstOperand();
            if (var10 instanceof IEVar) {
               Integer var11;
               if (var9 instanceof IEVar && (var11 = (Integer)var4.get(var9)) != null) {
                  var4.put((IEVar)var10, var11);
               } else {
                  OperationType var12 = EUtil.getOperation(var9, OperationType.ADD, OperationType.SUB);
                  if (var12 != null
                     && (var11 = (Integer)var4.get(((IEOperation)var9).getOperand1())) != null
                     && ((IEOperation)var9).getOperand2() instanceof IEImm) {
                     int var13 = (int)((IEImm)((IEOperation)var9).getOperand2()).getValueAsLong();
                     var11 = var11 + (var12 == OperationType.ADD ? var13 : -var13);
                     var4.put((IEVar)var10, var11);
                  } else if (var4.containsKey(var10)) {
                     var4.remove(var10);
                     if (var4.isEmpty()) {
                        break;
                     }
                  }
               }
            }

            if (var10 instanceof IEMem) {
               IEGeneric var20 = ((IEMem)var10).getReference();
               int var22 = var10.getBitsize();
               Integer var24 = this.pC(var20, var4);
               if (var24 != null) {
                  Long var14 = null;
                  if (var9 instanceof IEImm) {
                     var14 = ((IEImm)var9).getValueAsAddress();
                  }

                  if (var24 == 0 && var14 != null && var14 != 0L && this.kS(var14)) {
                     var5 = true;
                  }

                  var3.kS.add(new ans(var24, var22, var14));
               }
            }
         }
      }

      return var5;
   }

   private Integer pC(IEGeneric var1, Map var2) {
      if (var1 instanceof IEVar) {
         return (Integer)var2.get(var1);
      } else {
         OperationType var3 = EUtil.getOperation(var1, OperationType.ADD, OperationType.SUB);
         if (var3 != null) {
            IEOperation var4 = (IEOperation)var1;
            Integer var5;
            if (var4.getOperand2() instanceof IEImm && (var5 = (Integer)var2.get(var4.getOperand1())) != null) {
               int var6 = (int)((IEImm)var4.getOperand2()).getValueAsLong();
               return var5 + (var3 == OperationType.ADD ? var6 : -var6);
            }
         }

         return null;
      }
   }

   private boolean wS() {
      TreeSet var1 = new TreeSet();

      for (anw var3 : this.fI.values()) {
         for (ans var5 : var3.kS) {
            if (!var5.pC && var5.A % this.wS == 0 && var5.wS != null && this.kS(var5.wS)) {
               var3.wS.put(var5.A, var5.wS);
               var1.add(var5.wS);
            }
         }

         Assert.a(var3.wS.containsKey(0));
      }

      ArrayList var12 = new ArrayList();

      for (long var15 : var1) {
         Long var6 = (Long)var1.higher(var15);
         int var7 = var6 == null ? 256 : Math.min(256, (int)(var6 - var15) / this.UT);
         long var8 = var15;
         var12.clear();

         for (int var10 = 0; var10 < var7; var10++) {
            Long var11 = this.pC(var8, true);
            if (var11 == null) {
               break;
            }

            var12.add(var11);
            var8 += this.UT;
         }

         if (!var12.isEmpty()) {
            aod var30 = new aod(var15, var7, var12);
            this.WR.put(var15, var30);
         }
      }

      ArrayList var14 = new ArrayList();

      for (anw var20 : this.fI.values()) {
         if (!this.WR.containsKey(var20.wS.get(0))) {
            var14.add(var20.pC);
         } else {
            int var24 = -1;

            for (int var28 : var20.wS.descendingKeySet()) {
               if (!this.WR.containsKey(var20.wS.get(var28))) {
                  var24 = var28;
               }
            }

            if (var24 >= 0) {
               var20.wS = new TreeMap(var20.wS.subMap(0, var24));
            }
         }
      }

      for (long var21 : var14) {
         this.fI.remove(var21);
      }

      var14.clear();

      for (anw var22 : this.fI.values()) {
         aod var25 = (aod)this.WR.get(var22.wS.get(0));
         int var27 = 0;

         for (long var9 : var25.kS) {
            if (var9 == var22.pC) {
               break;
            }

            var27++;
         }

         if (var27 == 0) {
            var14.add(var22.pC);
         } else {
            while (var27 < var25.kS.size()) {
               var25.kS.remove(var27);
            }
         }
      }

      for (long var23 : var14) {
         this.fI.remove(var23);
      }

      var14.clear();
      return !this.fI.isEmpty();
   }

   protected final Long pC(long var1) {
      return this.pC(var1, false);
   }

   protected final Long pC(long var1, boolean var3) {
      try {
         boolean var4 = false;
         long var5 = this.A.getMemory().readPointer(var1);
         long var7 = this.A(var5);
         if (this.A.getInternalMethod(var7, true) != null) {
            var4 = true;
         } else if (var3 && this.A.getNativeItemAt(var7) == null) {
            int var9 = this.A.getMemory().readInt(var7);
            if (var9 != 0 && this.A.setRoutineAt(var7, 0, 0)) {
               var4 = true;
            }
         }

         return !var4 ? null : var7;
      } catch (MemoryException var10) {
         return null;
      }
   }

   protected long A(long var1) {
      return this.A.getProcessor().createEntryPoint(var1).getAddress();
   }

   private boolean kS(long var1) {
      try {
         long var3 = this.A.getMemory().readPointer(var1);
         this.A.getMemory().readByte(var3);
         return true;
      } catch (MemoryException var5) {
         return false;
      }
   }

   private boolean UT() {
      MultiMap var1 = new MultiMap();

      for (anw var3 : this.fI.values()) {
         for (long var5 : var3.pC()) {
            var1.put(var5, var3);
         }
      }

      MultiMap var13 = new MultiMap();

      for (anw var16 : this.fI.values()) {
         var13.put(new ArrayList(var16.pC()), var16);
      }

      ArrayList var15 = new ArrayList(this.fI.values());
      Collections.sort(var15, new any(this));
      HashMap var17 = new HashMap();

      while (!var15.isEmpty()) {
         anw var18 = (anw)var15.get(0);
         List var6 = var13.get(new ArrayList(var18.pC()));
         boolean var7 = true;

         for (anw var9 : var6) {
            if (!var15.contains(var9)) {
               var7 = false;
               break;
            }
         }

         if (var7) {
            ant var25 = this.NS.pC(var6, var18.pC(this.WR));

            for (anw var10 : var6) {
               var10.UT = var25;
            }

            for (long var31 : var18.pC()) {
               var17.put(var31, var25);
            }
         }

         var15.removeAll(var6);
      }

      this.fI.clear();

      for (ant var21 : this.NS) {
         for (anw var26 : var21.A) {
            this.fI.put(var26.pC, var26);
         }
      }

      if (this.fI.isEmpty()) {
         return false;
      } else {
         for (anw var22 : this.fI.values()) {
            TreeMap var24 = new TreeMap();
            byte var27 = 0;

            for (ans var32 : var22.kS) {
               int var11 = var32.A;
               if (var32.pC) {
                  if (var11 == 0) {
                     if (var27 == 0) {
                        var27 = 2;
                     } else if (var27 == 2) {
                        var27 = 22;
                     } else if (var27 == 1) {
                        var27 = 12;
                     }
                  }

                  anw var12 = (anw)this.fI.get(var32.wS);
                  if (var12 != null) {
                     var24.put(var11, var12.UT);
                  }
               } else {
                  if (var11 == 0) {
                     if (var27 == 0) {
                        var27 = 1;
                     } else if (var27 == 1) {
                        var27 = 11;
                     } else if (var27 == 2) {
                        var27 = 21;
                     }
                  }

                  ant var33 = (ant)var17.get(var32.wS);
                  if (var33 != null && var33 != var22.UT) {
                     var24.put(var11, var33);
                  }
               }
            }

            if (var27 == 12) {
               var24.clear();
            }

            if (var22.UT.wS == null || var22.UT.wS.size() < var24.size()) {
               var22.UT.wS = var24;
            }
         }

         this.NS.A();
         return true;
      }
   }

   private void E() {
      LinkedHashSet var1 = new LinkedHashSet();

      for (ant var3 : this.NS) {
         for (int var4 = 0; var4 < var3.kS.size(); var4++) {
            var1.add(new aob(var3, var4));
         }
      }

      while (!var1.isEmpty()) {
         aob var14 = (aob)var1.iterator().next();
         List var16 = this.NS.pC(var14.pC, var14.A);
         int var18 = 1;

         while (var18 < var16.size()) {
            aob var5 = (aob)var16.get(var18);
            aob var6 = var5.kS;
            int var7 = var6.A;
            int var8 = ((aod)var6.pC.kS.get(var7)).A();
            int var9 = var5.A;
            int var10 = ((aod)var5.pC.kS.get(var9)).A();
            if ((var10 < var8 || var9 > 0 && var10 != var8) && !this.pC((aod)var5.pC.kS.get(var9), var8)) {
               if (!this.pC((aod)var6.pC.kS.get(var7), var10)) {
                  throw new RuntimeException("Inconsistent vtables, failed adjusting");
               }

               var18 = 1;
            } else {
               var18++;
            }
         }

         var1.removeAll(var16);
      }

      LinkedHashSet var15 = new LinkedHashSet();
      MultiMap var17 = new MultiMap();

      for (ant var21 : this.NS) {
         int var23 = 0;

         for (aod var27 : var21.kS) {
            var27.pC();
            int var30 = 0;

            for (long var11 : var27.kS) {
               var15.add(new aoa(var21, var23, var30));
               INativeMethodItem var13 = this.A.getInternalMethod(var11, true);
               Assert.a(var13 != null, Strings.ff("Expected routine at address 0x%X", var11));
               var27.wS.add(var13);
               var17.put(var13, new aoa(var21, var23, var30));
               var30++;
            }

            var23++;
         }
      }

      while (!var15.isEmpty()) {
         aoa var20 = (aoa)var15.iterator().next();
         List var22 = this.NS.pC(var20.pC, var20.A, var20.kS);
         this.vP.add(var22);
         HashSet var24 = new HashSet(var22);
         HashSet var26 = new HashSet();

         for (aoa var31 : var24) {
            INativeMethodItem var34 = (INativeMethodItem)((aod)var31.pC.kS.get(var31.A)).wS.get(var31.kS);
            var26.add(var34);
         }

         for (INativeMethodItem var32 : var26) {
            ArrayList var35 = new ArrayList();

            for (aoa var12 : var17.get(var32)) {
               if (!var24.contains(var12)) {
                  var35.add(var12);
               }
            }

            if (!var35.isEmpty()) {
               INativeMethodItem var37 = this.ys.createMethodReference(var32.getName(true), var32.getPrototype(), var32.getData(), 2);
               this.ED.add(var37);
               var17.removeMulti(var32, var35);
               var17.putMulti(var37, var35);

               for (aoa var39 : var35) {
                  ((aod)var39.pC.kS.get(var39.A)).wS.set(var39.kS, var37);
               }
            }
         }

         var15.removeAll(var24);
      }
   }

   private boolean pC(aod var1, int var2) {
      int var3 = var1.kS.size();
      if (var2 == var3) {
         return true;
      } else if (var2 > var1.A) {
         return false;
      } else {
         if (var2 < var3) {
            if (var2 <= 0) {
               return false;
            }

            var1.kS = var1.kS.subList(0, var2);
         } else {
            long var4 = var1.pC + var3 * this.UT;

            for (int var6 = var3; var6 < var2; var6++) {
               Long var7 = this.pC(var4);
               if (var7 == null) {
                  return false;
               }

               var1.kS.add(var7);
               var4 += this.UT;
            }
         }

         return true;
      }
   }

   private void sY() {
      HashSet var1 = new HashSet();

      for (ant var3 : this.NS) {
         int var4 = var3.UT;
         int var5 = 0;

         for (List var7 : this.vP) {
            HashSet var8 = new HashSet();

            for (aoa var10 : var7) {
               INativeMethodItem var11 = (INativeMethodItem)((aod)var10.pC.kS.get(var10.A)).wS.get(var10.kS);
               var8.add(var11);
            }

            String var23 = "vmeth_" + Formatter.integerToAlphaString(var5).toUpperCase();

            for (INativeMethodItem var27 : var8) {
               if (!var27.isRenamed()) {
                  var27.setName(var23);
               }
            }

            var5++;
         }

         String var20 = Strings.ff("Class%03d", var4);
         IClassType var21 = this.E.createClassType(var20, 1, 0);
         INativeClassItem var22 = this.ld.createClass(var21);
         var3.sY = var21;
         ArrayList var24 = new ArrayList();
         ArrayList var26 = new ArrayList();
         int var28 = 0;

         for (aod var13 : var3.kS) {
            String var14;
            if (var3.kS.size() == 1) {
               var14 = Strings.ff("VtableClass%03d", var4);
            } else {
               var14 = Strings.ff("Vtable%d_Class%03d", var28, var4);
            }

            Couple var15 = this.E.createVirtualTableDefinition(var14, var13.pC, var13.wS);
            var24.add((IVirtualTableDefinition)var15.getFirst());
            var26.add((IMethodTable)var15.getSecond());
            var28++;
         }

         if (var3.wS.isEmpty()) {
            this.E.setClassVirtualTable(var21, (IVirtualTableDefinition)var24.get(0));
         } else {
            ArrayList var30 = new ArrayList();

            for (ant var37 : var3.wS.values()) {
               var30.add(var37.sY);
            }

            this.E.setClassSuperTypes(var21, var30, var24);
         }

         int var31 = var21.getSize();

         for (anw var38 : var3.A) {
            for (ans var16 : var38.kS) {
               if (!var16.pC) {
                  int var17 = var16.A;
                  if (var17 >= var31) {
                     int var18 = var16.kS / 8;
                     INativeType var19 = this.E.getExactInteger(var18, true);
                     if (var19 != null) {
                        this.E.addStructureField(var21, null, var19, var17, 0, 0, 0);
                     }
                  }
               }
            }
         }

         this.E.completeClassTypeInitialization(var21);
         this.ld.setVirtualTableMethods(var22, var26);
         var28 = 0;

         for (IVirtualTableDefinition var39 : var24) {
            long var43 = ((IMethodTable)var26.get(var28)).getAddress();
            this.A.setDataAt(var43, var39.getVirtualTableType(), null);
            var28++;
         }

         for (IMethodTable var40 : var26) {
            for (INativeMethodItem var46 : var40.getAll()) {
               if (!var1.contains(var46)) {
                  var46.addFlags(268435456);
                  this.pC(var21, var46);
                  var1.add(var46);
               }
            }
         }

         int var36 = 0;

         for (anw var45 : var3.A) {
            INativeMethodItem var47 = var45.A;
            var47.addFlags(65536);
            this.ld.addNonVirtualMethod(var22, var47);
            this.pC(var21, var47);
            if (!var47.isRenamed()) {
               String var48 = Strings.ff("%s_%d", var20, var36);
               var47.setName(var48);
            }

            var36++;
         }

         this.ld.completeClassInitialization(var22);
      }
   }

   protected boolean pC(IClassType var1, INativeMethodItem var2) {
      IPrototypeItem var3 = var2.getPrototype();
      if (var3 == null) {
         return false;
      } else {
         IReferenceType var4 = this.E.createReference(var1);
         ArrayList var5 = new ArrayList(var3.getParameterTypes());
         if (var5.isEmpty()) {
            var5.add(var4);
         } else {
            var5.set(0, var4);
         }

         INativeType var6 = var3.getReturnType();
         if ((var2.getGenericFlags() & 65536) != 0) {
            var6 = null;
         }

         ICallingConvention var7 = var3.getCallingConvention();
         IPrototypeItem var8 = this.E.createPrototype(var7, var6, var5, null);
         var2.setPrototype(var8);
         return true;
      }
   }

   private void ys() {
      HashSet var1 = new HashSet();

      for (ant var3 : this.NS) {
         IClassType var4 = var3.sY;
         INativeClassItem var5 = var4.getClassItem();
         this.xC.add(var5);

         for (anw var7 : var3.A) {
            if (!var1.contains(var7.A)) {
               var1.add(var7.A);
               this.sY.moveToClass(var7.A, var5);
            }
         }

         for (aod var11 : var3.kS) {
            for (INativeMethodItem var9 : var11.wS) {
               if (!var1.contains(var9)) {
                  var1.add(var9);
                  this.sY.moveToClass(var9, var5);
               }
            }
         }
      }
   }
}
