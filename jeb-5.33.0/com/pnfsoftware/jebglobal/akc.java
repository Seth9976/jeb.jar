package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COperatorType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICConstant;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElementFactory;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICOperator;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICOperatorFactory;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICTypeFactory;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.EvaluationException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.IllegalIntermediateExpressionException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EDefUseInfo;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EState;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.ETypeInfo;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.FunctionOptype;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardTypeManager;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IReferenceType;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeUtil;
import com.pnfsoftware.jeb.util.collect.CollectionUtil;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Ser
public class akc extends ala implements IEOperation {
   private static final ILogger pC = GlobalLog.getLogger(akc.class);
   @SerId(1)
   private OperationType A;
   @SerId(2)
   private int kS;
   @SerId(3)
   private IEGeneric wS;
   @SerId(4)
   private IEGeneric UT;
   @SerId(5)
   private IEGeneric[] E;
   @SerId(6)
   private FunctionOptype sY;

   public static IEOperation pC(IEGeneric var0, int var1, boolean var2) {
      int var3 = var0.getBitsize();
      OperationType var4 = var2 && var1 > var3 ? OperationType.CAST_S : OperationType.CAST;
      return new akc(var4, var1, var0, null);
   }

   public static IEOperation pC(OperationType var0, IEGeneric var1, int var2) {
      if (var0 != null && var0.isConversion()) {
         return new akc(var0, var2, var1, null);
      } else {
         throw new IllegalArgumentException();
      }
   }

   public akc(OperationType var1, IEGeneric var2) {
      this(var1, var2, null);
   }

   public akc(OperationType var1, IEGeneric var2, IEGeneric var3) {
      if (var1 == null) {
         throw new NullPointerException("Null operator");
      } else if (var1 == OperationType.FUNCTION) {
         throw new NullPointerException("Custom operations (functions) cannot be created with this constructor");
      } else if (var2 == null) {
         throw new NullPointerException("Null first operand");
      } else {
         this.A = var1;
         this.wS = var2;
         this.UT = var3;
         this.kS = this.kS();
         this.verify();
      }
   }

   public akc(FunctionOptype var1, IEGeneric... var2) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.sY = var1;
         this.A = OperationType.FUNCTION;
         var1.validateOperands(var2);
         if (var2.length >= 1) {
            this.wS = var2[0];
         }

         if (var2.length >= 2) {
            this.UT = var2[1];
         }

         if (var2.length >= 3) {
            this.E = (IEGeneric[])Arrays.copyOfRange(var2, 2, var2.length);
         }

         this.kS = var1.getResultBitsize(var2);
      }
   }

   private akc(OperationType var1, int var2, IEGeneric var3, IEGeneric var4) {
      this.A = var1;
      this.kS = var2;
      this.wS = var3;
      this.UT = var4;
   }

   private akc(FunctionOptype var1, int var2, IEGeneric var3, IEGeneric var4, IEGeneric... var5) {
      this.A = OperationType.FUNCTION;
      this.sY = var1;
      this.kS = var2;
      this.wS = var3;
      this.UT = var4;
      this.E = var5;
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   private int kS() {
      // $VF: Couldn't be decompiled
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      // java.lang.RuntimeException: invalid constant type: Lcom/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/OperationType; with value 13
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ConstExprent.toJava(ConstExprent.java:356)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SwitchStatement.toJava(SwitchStatement.java:171)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.RootStatement.toJava(RootStatement.java:36)
      //   at org.jetbrains.java.decompiler.main.ClassWriter.writeMethod(ClassWriter.java:1326)
      //
      // Bytecode:
      // 000: getstatic com/pnfsoftware/jebglobal/akd.pC [I
      // 003: aload 0
      // 004: getfield com/pnfsoftware/jebglobal/akc.A Lcom/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/OperationType;
      // 007: invokevirtual com/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/OperationType.ordinal ()I
      // 00a: iaload
      // 00b: tableswitch 287 1 59 249 249 249 249 249 249 249 249 249 249 249 249 249 249 249 249 249 249 249 249 249 249 251 251 251 251 251 251 251 251 251 251 251 251 251 251 251 251 251 251 251 251 251 261 261 261 261 273 273 273 273 283 283 283 283 283 283 283 285
      // 104: bipush 1
      // 105: ireturn
      // 106: aload 0
      // 107: getfield com/pnfsoftware/jebglobal/akc.wS Lcom/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/IEGeneric;
      // 10a: invokeinterface com/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/IEGeneric.getBitsize ()I 1
      // 10f: ireturn
      // 110: bipush 2
      // 111: aload 0
      // 112: getfield com/pnfsoftware/jebglobal/akc.wS Lcom/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/IEGeneric;
      // 115: invokeinterface com/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/IEGeneric.getBitsize ()I 1
      // 11a: imul
      // 11b: ireturn
      // 11c: aload 0
      // 11d: getfield com/pnfsoftware/jebglobal/akc.wS Lcom/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/IEGeneric;
      // 120: invokeinterface com/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/IEGeneric.getBitsize ()I 1
      // 125: ireturn
      // 126: bipush -1
      // 127: ireturn
      // 128: bipush -1
      // 129: ireturn
      // 12a: new java/lang/RuntimeException
      // 12d: dup
      // 12e: aload 0
      // 12f: getfield com/pnfsoftware/jebglobal/akc.A Lcom/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/OperationType;
      // 132: invokedynamic makeConcatWithConstants (Lcom/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/OperationType;)Ljava/lang/String; bsm=java/lang/invoke/StringConcatFactory.makeConcatWithConstants (Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite; args=[ "No bitsize for IR operator: \u0001" ]
      // 137: invokespecial java/lang/RuntimeException.<init> (Ljava/lang/String;)V
      // 13a: athrow
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   @Override
   public void verify() throws IllegalIntermediateExpressionException {
      // $VF: Couldn't be decompiled
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      // java.lang.RuntimeException: invalid constant type: Lcom/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/OperationType; with value 21
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ConstExprent.toJava(ConstExprent.java:356)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SwitchStatement.toJava(SwitchStatement.java:171)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:251)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.RootStatement.toJava(RootStatement.java:36)
      //   at org.jetbrains.java.decompiler.main.ClassWriter.writeMethod(ClassWriter.java:1326)
      //
      // Bytecode:
      // 000: aload 0
      // 001: invokevirtual com/pnfsoftware/jebglobal/akc.isCustomOperation ()Z
      // 004: ifeq 008
      // 007: return
      // 008: aload 0
      // 009: getfield com/pnfsoftware/jebglobal/akc.UT Lcom/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/IEGeneric;
      // 00c: ifnull 013
      // 00f: bipush 2
      // 010: goto 014
      // 013: bipush 1
      // 014: istore 1
      // 015: aload 0
      // 016: getfield com/pnfsoftware/jebglobal/akc.A Lcom/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/OperationType;
      // 019: iload 1
      // 01a: invokevirtual com/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/OperationType.isValid (I)Z
      // 01d: ifne 03c
      // 020: new com/pnfsoftware/jeb/core/units/code/asm/decompiler/exceptions/IllegalIntermediateExpressionException
      // 023: dup
      // 024: ldc "Invalid number of operands for operation type %s (got %d)"
      // 026: bipush 2
      // 027: anewarray 90
      // 02a: dup
      // 02b: bipush 0
      // 02c: aload 0
      // 02d: getfield com/pnfsoftware/jebglobal/akc.A Lcom/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/OperationType;
      // 030: aastore
      // 031: dup
      // 032: bipush 1
      // 033: iload 1
      // 034: invokestatic java/lang/Integer.valueOf (I)Ljava/lang/Integer;
      // 037: aastore
      // 038: invokespecial com/pnfsoftware/jeb/core/units/code/asm/decompiler/exceptions/IllegalIntermediateExpressionException.<init> (Ljava/lang/String;[Ljava/lang/Object;)V
      // 03b: athrow
      // 03c: aload 0
      // 03d: getfield com/pnfsoftware/jebglobal/akc.A Lcom/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/OperationType;
      // 040: invokevirtual com/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/OperationType.isConversion ()Z
      // 043: ifeq 047
      // 046: return
      // 047: getstatic com/pnfsoftware/jebglobal/akd.pC [I
      // 04a: aload 0
      // 04b: getfield com/pnfsoftware/jebglobal/akc.A Lcom/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/OperationType;
      // 04e: invokevirtual com/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/OperationType.ordinal ()I
      // 051: iaload
      // 052: tableswitch 337 1 51 334 334 334 218 218 218 218 218 218 218 218 218 218 334 218 218 218 218 218 218 218 218 218 218 218 218 218 218 218 218 218 218 334 334 334 334 334 334 218 218 218 218 218 218 218 290 290 218 218 218 218
      // 12c: aload 0
      // 12d: getfield com/pnfsoftware/jebglobal/akc.wS Lcom/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/IEGeneric;
      // 130: invokeinterface com/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/IEGeneric.getBitsize ()I 1
      // 135: aload 0
      // 136: getfield com/pnfsoftware/jebglobal/akc.UT Lcom/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/IEGeneric;
      // 139: invokeinterface com/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/IEGeneric.getBitsize ()I 1
      // 13e: if_icmpeq 1ad
      // 141: new com/pnfsoftware/jeb/core/units/code/asm/decompiler/exceptions/IllegalIntermediateExpressionException
      // 144: dup
      // 145: ldc "The operands op1 and op2 do not have the same bitsize: %d, %d (operator: %s)"
      // 147: bipush 3
      // 148: anewarray 90
      // 14b: dup
      // 14c: bipush 0
      // 14d: aload 0
      // 14e: getfield com/pnfsoftware/jebglobal/akc.wS Lcom/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/IEGeneric;
      // 151: invokeinterface com/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/IEGeneric.getBitsize ()I 1
      // 156: invokestatic java/lang/Integer.valueOf (I)Ljava/lang/Integer;
      // 159: aastore
      // 15a: dup
      // 15b: bipush 1
      // 15c: aload 0
      // 15d: getfield com/pnfsoftware/jebglobal/akc.UT Lcom/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/IEGeneric;
      // 160: invokeinterface com/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/IEGeneric.getBitsize ()I 1
      // 165: invokestatic java/lang/Integer.valueOf (I)Ljava/lang/Integer;
      // 168: aastore
      // 169: dup
      // 16a: bipush 2
      // 16b: aload 0
      // 16c: getfield com/pnfsoftware/jebglobal/akc.A Lcom/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/OperationType;
      // 16f: aastore
      // 170: invokespecial com/pnfsoftware/jeb/core/units/code/asm/decompiler/exceptions/IllegalIntermediateExpressionException.<init> (Ljava/lang/String;[Ljava/lang/Object;)V
      // 173: athrow
      // 174: aload 0
      // 175: getfield com/pnfsoftware/jebglobal/akc.wS Lcom/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/IEGeneric;
      // 178: invokeinterface com/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/IEGeneric.getBitsize ()I 1
      // 17d: bipush 2
      // 17e: aload 0
      // 17f: getfield com/pnfsoftware/jebglobal/akc.UT Lcom/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/IEGeneric;
      // 182: invokeinterface com/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/IEGeneric.getBitsize ()I 1
      // 187: imul
      // 188: if_icmpeq 1ad
      // 18b: new com/pnfsoftware/jeb/core/units/code/asm/decompiler/exceptions/IllegalIntermediateExpressionException
      // 18e: dup
      // 18f: ldc "Unexpected bitsizes for %s"
      // 191: bipush 1
      // 192: anewarray 90
      // 195: dup
      // 196: bipush 0
      // 197: aload 0
      // 198: getfield com/pnfsoftware/jebglobal/akc.A Lcom/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/OperationType;
      // 19b: aastore
      // 19c: invokespecial com/pnfsoftware/jeb/core/units/code/asm/decompiler/exceptions/IllegalIntermediateExpressionException.<init> (Ljava/lang/String;[Ljava/lang/Object;)V
      // 19f: athrow
      // 1a0: goto 1ad
      // 1a3: new java/lang/RuntimeException
      // 1a6: dup
      // 1a7: ldc "TBI"
      // 1a9: invokespecial java/lang/RuntimeException.<init> (Ljava/lang/String;)V
      // 1ac: athrow
      // 1ad: aload 0
      // 1ae: invokevirtual com/pnfsoftware/jebglobal/akc.kS ()I
      // 1b1: istore 2
      // 1b2: aload 0
      // 1b3: getfield com/pnfsoftware/jebglobal/akc.kS I
      // 1b6: iload 2
      // 1b7: if_icmpeq 1d9
      // 1ba: new com/pnfsoftware/jeb/core/units/code/asm/decompiler/exceptions/IllegalIntermediateExpressionException
      // 1bd: dup
      // 1be: ldc "Illegal bitsize: currently %d, expected to be %d"
      // 1c0: bipush 2
      // 1c1: anewarray 90
      // 1c4: dup
      // 1c5: bipush 0
      // 1c6: aload 0
      // 1c7: getfield com/pnfsoftware/jebglobal/akc.kS I
      // 1ca: invokestatic java/lang/Integer.valueOf (I)Ljava/lang/Integer;
      // 1cd: aastore
      // 1ce: dup
      // 1cf: bipush 1
      // 1d0: iload 2
      // 1d1: invokestatic java/lang/Integer.valueOf (I)Ljava/lang/Integer;
      // 1d4: aastore
      // 1d5: invokespecial com/pnfsoftware/jeb/core/units/code/asm/decompiler/exceptions/IllegalIntermediateExpressionException.<init> (Ljava/lang/String;[Ljava/lang/Object;)V
      // 1d8: athrow
      // 1d9: return
   }

   @Override
   public int hashCode() {
      int var1 = super.hashCode();
      var1 = 31 * var1 + (this.A == null ? 0 : this.A.hashCode());
      var1 = 31 * var1 + this.kS;
      var1 = 31 * var1 + (this.wS == null ? 0 : this.wS.hashCode());
      var1 = 31 * var1 + (this.UT == null ? 0 : this.UT.hashCode());
      var1 = 31 * var1 + (this.E == null ? 0 : Arrays.hashCode((Object[])this.E));
      return 31 * var1 + (this.sY == null ? 0 : this.sY.hashCode());
   }

   @Override
   public boolean equals(Object var1) {
      return this.equalsEx(var1, true);
   }

   @Override
   public boolean equalsEx(Object var1, boolean var2) {
      if (this == var1) {
         return true;
      } else if (!super.equalsEx(var1, var2)) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         akc var3 = (akc)var1;
         if (this.A != var3.A) {
            return false;
         } else if (this.kS != var3.kS) {
            return false;
         } else if (this.sY != var3.sY) {
            return false;
         } else {
            if (this.wS == null) {
               if (var3.wS != null) {
                  return false;
               }
            } else if (!this.wS.equalsEx(var3.wS, var2)) {
               return false;
            }

            if (this.UT == null) {
               if (var3.UT != null) {
                  return false;
               }
            } else if (!this.UT.equalsEx(var3.UT, var2)) {
               return false;
            }

            if (this.E == null) {
               if (var3.E != null) {
                  return false;
               }
            } else {
               if (var3.E == null) {
                  return false;
               }

               if (var3.E.length != this.E.length) {
                  return false;
               }

               for (int var4 = 0; var4 < this.E.length; var4++) {
                  if (!this.E[var4].equalsEx(var3.E[var4], var2)) {
                     return false;
                  }
               }
            }

            return true;
         }
      }
   }

   @Override
   public OperationType getOperationType() {
      return this.A;
   }

   public void pC(OperationType var1) {
      this.A = var1;
   }

   @Override
   public FunctionOptype getCustomOperationType() {
      return this.sY;
   }

   @Override
   public IEGeneric getOperand1() {
      return this.wS;
   }

   @Override
   public IEGeneric getOperand2() {
      return this.UT;
   }

   @Override
   public IEGeneric getOperand(int var1) {
      if (var1 < 0) {
         return null;
      } else if (var1 == 0) {
         return this.wS;
      } else if (var1 == 1) {
         return this.UT;
      } else {
         int var2 = var1 - 2;
         return var2 >= 0 && this.E != null && var2 < this.E.length ? this.E[var2] : null;
      }
   }

   @Override
   public List getOperands() {
      if (this.isCustomOperation()) {
         ArrayList var1 = new ArrayList(this.getCountOfOperands());
         if (this.wS != null) {
            var1.add(this.wS);
         }

         if (this.UT != null) {
            var1.add(this.UT);
         }

         if (this.E != null) {
            var1.addAll(Arrays.asList(this.E));
         }

         return var1;
      } else {
         return this.UT == null ? Arrays.asList(this.wS) : Arrays.asList(this.wS, this.UT);
      }
   }

   @Override
   public String getOperationName() {
      return this.isCustomOperation() ? this.sY.getName() : this.A.toString();
   }

   @Override
   public int getCountOfOperands() {
      if (this.isCustomOperation()) {
         int var1 = 0;
         if (this.wS != null) {
            var1++;
         }

         if (this.UT != null) {
            var1++;
         }

         if (this.E != null) {
            var1 += this.E.length;
         }

         return var1;
      } else {
         return this.UT == null ? 1 : 2;
      }
   }

   @Override
   public int getBitsize() {
      return this.kS;
   }

   @Override
   public int getPriority() {
      return 10;
   }

   @Override
   public void getUsed(EDefUseInfo var1) {
      if (this.wS != null) {
         this.wS.getUsed(var1);
      }

      if (this.UT != null) {
         this.UT.getUsed(var1);
      }

      if (this.E != null) {
         for (IEGeneric var5 : this.E) {
            var5.getUsed(var1);
         }
      }
   }

   @Override
   public void getDefinedOrUsedAsDestination(EDefUseInfo var1) {
   }

   @Override
   public boolean accessesMemory() {
      if (this.wS != null && this.wS.accessesMemory()) {
         return true;
      } else if (this.UT != null && this.UT.accessesMemory()) {
         return true;
      } else {
         if (this.E != null) {
            for (IEGeneric var4 : this.E) {
               if (var4.accessesMemory()) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   private void pC(IEGeneric var1, IEGeneric var2) {
      if (!this.A.isAnyOf(OperationType.LOG_OR, OperationType.LOG_AND, OperationType.LOG_NOT)) {
         A(var1, var2);
      }
   }

   @Override
   public int replaceVar(IEVar var1, IEGeneric var2) throws IllegalIntermediateExpressionException {
      int var3 = 0;
      if (this.wS != null) {
         if (this.wS == var1) {
            this.pC(this.wS, var2);
            this.wS = var2.duplicate();
            var3++;
         } else {
            var3 += this.wS.replaceVar(var1, var2);
         }
      }

      if (this.UT != null) {
         if (this.UT == var1) {
            this.pC(this.UT, var2);
            this.UT = var2.duplicate();
            var3++;
         } else {
            var3 += this.UT.replaceVar(var1, var2);
         }
      }

      if (this.E != null) {
         for (int var4 = 0; var4 < this.E.length; var4++) {
            if (this.E[var4] == var1) {
               this.pC(this.E[var4], var2);
               this.E[var4] = var2.duplicate();
               var3++;
            } else {
               var3 += this.E[var4].replaceVar(var1, var2);
            }
         }
      }

      return var3;
   }

   @Override
   public void collectSubExpressions(Collection var1) {
      CollectionUtil.addNonNulls(var1, this.wS, this.UT);
      CollectionUtil.addNonNulls(var1, this.E);
   }

   @Override
   public boolean replaceSubExpression(IEGeneric var1, IEGeneric var2) throws IllegalIntermediateExpressionException {
      pC(var1, var2);
      if (this.wS == var1) {
         this.pC(this.wS, var2);
         this.wS = var2;
         return true;
      } else if (this.UT == var1) {
         if (this.A.isShift()) {
            pC(var2);
         } else {
            this.pC(this.UT, var2);
         }

         this.UT = var2;
         return true;
      } else {
         if (this.E != null) {
            for (int var3 = 0; var3 < this.E.length; var3++) {
               if (this.E[var3] == var1) {
                  this.pC(this.E[var3], var2);
                  this.E[var3] = var2;
                  return true;
               }
            }
         }

         return false;
      }
   }

   @Override
   public void updateTypes(ETypeInfo var1) {
      IWildcardTypeManager var2 = var1.getWildcardTypeManager();
      if (this.wS != null) {
         this.wS.updateTypes(var1);
      }

      if (this.UT != null) {
         this.UT.updateTypes(var1);
      }

      if (this.E != null) {
         for (IEGeneric var6 : this.E) {
            var6.updateTypes(var1);
         }
      }

      if (!this.isCustomOperation()) {
         if (this.A == OperationType.ADD && this.wS.getType() != null && this.wS.getType().isDefined() && this.wS.getType().isPointer()) {
            IReferenceType var9 = (IReferenceType)TypeUtil.getNonAlias(this.wS.getType().getNativeType(), IReferenceType.class);
            INativeType var11 = var9.getPointedType();
            if (var11.getSize() != 0 && this.UT.isImm() && this.UT.asImm().canReadAsLong() && this.UT.asImm().getValueAsLong() % var11.getSize() == 0L) {
               this.setType(var2.create(var9), var1);
               return;
            }
         }

         IWildcardType.Group var10 = this.wS.getType() == null ? null : this.wS.getType().getGroup();
         IWildcardType.Group var12 = null;
         if (this.UT != null) {
            var12 = this.UT.getType() == null ? null : this.UT.getType().getGroup();
         }

         IWildcardType var13 = this.safelyType(var2);
         if (!var13.isDefined() && var13.getGroup() == null) {
            switch (this.A) {
               case ADD:
                  if (var10 != IWildcardType.Group.UINT || var12 != IWildcardType.Group.UINT && var12 != null) {
                     if (var10 != IWildcardType.Group.INTEGER || var12 != IWildcardType.Group.INTEGER && var12 != null) {
                        if ((var10 != IWildcardType.Group.INTEGER || var12 != IWildcardType.Group.INTEGER)
                           && (var10 != IWildcardType.Group.POINTER || var12 != IWildcardType.Group.POINTER)) {
                           if (var10 == IWildcardType.Group.INTEGER && var12 == IWildcardType.Group.POINTER
                              || var10 == IWildcardType.Group.POINTER && var12 == IWildcardType.Group.INTEGER) {
                              this.setType(var13.updateGroup(IWildcardType.Group.POINTER), var1);
                           }
                        } else {
                           this.setType(var13.updateGroup(IWildcardType.Group.INTEGER), var1);
                        }
                     } else {
                        this.setType(var13.updateGroup(IWildcardType.Group.INTEGER), var1);
                     }
                  } else {
                     this.setType(var13.updateGroup(IWildcardType.Group.UINT), var1);
                  }
                  break;
               case SUB:
                  if (var10 == IWildcardType.Group.UINT && var12 == IWildcardType.Group.UINT) {
                     this.setType(var13.updateGroup(IWildcardType.Group.UINT), var1);
                  } else if (var10 == IWildcardType.Group.INTEGER || var12 == IWildcardType.Group.POINTER) {
                     this.setType(var13.updateGroup(IWildcardType.Group.INTEGER), var1);
                  }
                  break;
               case MUL:
               case DIV_S:
               case DIV_U:
               case REM_S:
               case REM_U:
               case XOR:
               case ROL:
               case ROR:
               case SAR:
               case SHL:
               case SHR:
                  if (var10 != IWildcardType.Group.UINT || var12 != IWildcardType.Group.UINT && var12 != null) {
                     if (var10 == IWildcardType.Group.INTEGER && (var12 == IWildcardType.Group.INTEGER || var12 == null)) {
                        this.setType(var13.updateGroup(IWildcardType.Group.INTEGER), var1);
                     }
                  } else {
                     this.setType(var13.updateGroup(IWildcardType.Group.UINT), var1);
                  }
               case AND:
               case OR:
               case POW:
               case ADD_SSAT:
               case ADD_USAT:
               case SUB_SSAT:
               case SUB_USAT:
               case MUL2_S:
               case MUL2_U:
               case DIV2_S:
               case DIV2_U:
               case CAST:
               case CAST_S:
               case FP2FP:
               case FP2INT:
               case FP2UINT:
               default:
                  break;
               case NOT:
                  if (var10 == IWildcardType.Group.UINT) {
                     this.setType(var13.updateGroup(IWildcardType.Group.UINT), var1);
                  } else if (var10 == IWildcardType.Group.INTEGER) {
                     this.setType(var13.updateGroup(IWildcardType.Group.INTEGER), var1);
                  }
                  break;
               case FADD:
               case FSUB:
               case FMUL:
               case FDIV:
               case INT2FP:
               case UINT2FP:
                  this.setType(var13.updateGroup(IWildcardType.Group.FLOAT), var1);
            }
         }

         IWildcardType.Group var14 = null;
         switch (this.A) {
            case FEQ:
            case FNE:
            case FLT:
            case FGT:
            case FLE:
            case FGE:
            case FADD:
            case FSUB:
            case FMUL:
            case FDIV:
            case FP2FP:
            case FP2INT:
            case FP2UINT:
               this.wS = this.pC(this.wS);
               IWildcardType var15 = this.wS.safelyType(var2);
               if (var15.isUpdatable()) {
                  this.wS.setType(var15.updateGroup(IWildcardType.Group.FLOAT));
               }

               if (this.UT != null) {
                  this.UT = this.pC(this.UT);
                  IWildcardType var18 = this.UT.safelyType(var2);
                  if (var18.isUpdatable()) {
                     this.UT.setType(var18.updateGroup(IWildcardType.Group.FLOAT));
                  }
               }
            case FUN:
            case ADD:
            case SUB:
            case MUL:
            case AND:
            case OR:
            case XOR:
            case ROL:
            case ROR:
            case SAR:
            case SHL:
            case SHR:
            case NOT:
            case POW:
            case ADD_SSAT:
            case ADD_USAT:
            case SUB_SSAT:
            case SUB_USAT:
            case MUL2_S:
            case MUL2_U:
            case DIV2_S:
            case DIV2_U:
            case CAST:
            case CAST_S:
            case INT2FP:
            default:
               break;
            case DIV_U:
            case REM_U:
               var14 = IWildcardType.Group.UINT;
            case DIV_S:
            case REM_S:
               if (var14 == null) {
                  var14 = IWildcardType.Group.INTEGER;
               }

               this.wS = this.pC(this.wS);
               IWildcardType var7 = this.wS.safelyType(var2);
               if (!var7.isDefined() && var7.getGroup() == null) {
                  this.wS.setType(var7.updateGroup(var14), var1);
               }

               this.UT = this.pC(this.UT);
               IWildcardType var8 = this.UT.safelyType(var2);
               if (!var8.isDefined() && var8.getGroup() == null) {
                  this.UT.setType(var8.updateGroup(var14), var1);
               }
         }

         if (this.A == OperationType.ADD || this.A == OperationType.SUB) {
            if (var13.isWildcardPointer() && var13.getPointedGroup() != null) {
               IWildcardType var16 = this.wS.getType();
               if (var16 != null && var16.isWildcardPointer() && var16.getPointedGroup() == null) {
                  this.wS.setType(var16.updatePointedGroup(var13.getPointedGroup()), var1);
               } else {
                  IWildcardType var19 = this.UT.getType();
                  if (var19 != null && var19.isWildcardPointer() && var19.getPointedGroup() == null) {
                     this.UT.setType(var19.updatePointedGroup(var13.getPointedGroup()), var1);
                  }
               }
            }

            if (var13.isWildcardPointer() && var13.getPointedGroup() == null) {
               IWildcardType var17 = this.wS.getType();
               if (var17 != null && var17.isWildcardPointer() && var17.getPointedGroup() != null) {
                  this.setType(var13.updatePointedGroup(var17.getPointedGroup()), var1);
               } else {
                  IWildcardType var20 = this.UT.getType();
                  if (var20 != null && var20.isWildcardPointer() && var20.getPointedGroup() != null) {
                     this.setType(var13.updatePointedGroup(var20.getPointedGroup()), var1);
                  }
               }
            }
         }

         if (this.wS != null
            && this.UT != null
            && (this.wS.getSafeType(var2).isPointer() || this.UT.getSafeType(var2).isPointer())
            && !this.getSafeType(var2).isPointer()) {
            this.setType(var2.createPointer(0), var1);
         }
      }
   }

   private IEGeneric pC(IEGeneric var1) {
      return (IEGeneric)(var1 instanceof IEImm && !((IEImm)var1).isMutable() ? ((IEImm)var1).duplicateToMutable() : var1);
   }

   public akc pC() {
      IEGeneric var1 = this.wS == null ? null : this.wS.duplicate();
      IEGeneric var2 = this.UT == null ? null : this.UT.duplicate();
      IEGeneric[] var3 = null;
      if (this.E != null) {
         var3 = new IEGeneric[this.E.length];

         for (int var4 = 0; var4 < this.E.length; var4++) {
            var3[var4] = this.E[var4].duplicate();
         }
      }

      akc var5;
      if (this.isCustomOperation()) {
         var5 = new akc(this.sY, this.kS, var1, var2, var3);
      } else {
         var5 = new akc(this.A, this.kS, var1, var2);
      }

      return (akc)this.pC(var5);
   }

   @Override
   public IEImm evaluate(EState var1) {
      if (this.isCustomOperation()) {
         throw new EvaluationException("Cannot evaluate custom function: " + this.sY);
      } else if (!this.A.isValid(this.UT == null ? 1 : 2)) {
         throw new RuntimeException(Strings.ff("Invalid operation type \"%s\" for parameters (%s, %s)", this.A, this.wS, Strings.safe(this.UT)));
      } else {
         switch (this.A) {
            case AND:
               if (this.wS instanceof IEImm && ((IEImm)this.wS).isZero()) {
                  return EUtil.zero(this.wS.getBitsize());
               }

               if (this.UT instanceof IEImm && ((IEImm)this.UT).isZero()) {
                  return EUtil.zero(this.wS.getBitsize());
               }
               break;
            case XOR:
               if (this.wS.equals(this.UT)) {
                  return EUtil.zero(this.wS.getBitsize());
               }
         }

         IEImm var2 = null;
         if (this.wS != null) {
            var2 = this.wS.evaluate(var1);
            if (var2 == null) {
               return null;
            }
         }

         IEImm var3 = null;
         if (this.UT != null) {
            var3 = this.UT.evaluate(var1);
            if (var3 == null) {
               return null;
            }
         }

         if (this.isCustomOperation()) {
            ArrayList var22 = new ArrayList();
            if (var2 != null) {
               var22.add(var2);
            }

            if (var3 != null) {
               var22.add(var3);
            }

            if (this.E != null) {
               for (IEGeneric var38 : this.E) {
                  IEImm var9 = var38.evaluate(var1);
                  if (var9 == null) {
                     return null;
                  }

                  var22.add(var9);
               }
            }

            throw new RuntimeException();
         } else {
            switch (this.A) {
               case LOG_AND:
                  return var2._signum() != 0 && var3._signum() != 0 ? ajr.pC(1L, 1) : ajr.pC(0L, 1);
               case LOG_OR:
                  return var2._signum() == 0 && var3._signum() == 0 ? ajr.pC(0L, 1) : ajr.pC(1L, 1);
               case LOG_NOT:
                  return var2._signum() != 0 ? ajr.pC(0L, 1) : ajr.pC(1L, 1);
               case LOG_EQ:
                  return var2._cmp(var3) == 0 ? ajr.pC(1L, 1) : ajr.pC(0L, 1);
               case LOG_NEQ:
                  return var2._cmp(var3) != 0 ? ajr.pC(1L, 1) : ajr.pC(0L, 1);
               case GT_S:
                  return var2._cmp(var3) > 0 ? ajr.pC(1L, 1) : ajr.pC(0L, 1);
               case GE_S:
                  return var2._cmp(var3) >= 0 ? ajr.pC(1L, 1) : ajr.pC(0L, 1);
               case LT_S:
                  return var2._cmp(var3) < 0 ? ajr.pC(1L, 1) : ajr.pC(0L, 1);
               case LE_S:
                  return var2._cmp(var3) <= 0 ? ajr.pC(1L, 1) : ajr.pC(0L, 1);
               case GT_U:
                  return var2._cmpU(var3) > 0 ? ajr.pC(1L, 1) : ajr.pC(0L, 1);
               case GE_U:
                  return var2._cmpU(var3) >= 0 ? ajr.pC(1L, 1) : ajr.pC(0L, 1);
               case LT_U:
                  return var2._cmpU(var3) < 0 ? ajr.pC(1L, 1) : ajr.pC(0L, 1);
               case LE_U:
                  return var2._cmpU(var3) <= 0 ? ajr.pC(1L, 1) : ajr.pC(0L, 1);
               case PAR:
                  IEImm var21 = var2;

                  int var27;
                  for (var27 = 0; var21._signum() != 0; var21 = var21._shr(1)) {
                     if (var21._testbit(0)) {
                        var27++;
                     }
                  }

                  return var27 % 2 == 0 ? ajr.pC(1L, 1) : ajr.pC(0L, 1);
               case CARRY:
                  IEImm var20 = var2._add(var3);
                  IEImm var26 = var2._xor(var3)._xor(var20);
                  IEImm var35 = var2._xor(var20);
                  IEImm var7 = var2._xor(var3)._not();
                  IEImm var8 = var26._xor(var35._and(var7));
                  return var8._testbit(var2.getBitsize() - 1) ? ajr.pC(1L, 1) : ajr.pC(0L, 1);
               case FEQ:
                  Integer var19 = var2._fcmp(var3);
                  return var19 != null && var19 == 0 ? ajr.pC(1L, 1) : ajr.pC(0L, 1);
               case FNE:
                  Integer var18 = var2._fcmp(var3);
                  return var18 != null && var18 != 0 ? ajr.pC(1L, 1) : ajr.pC(0L, 1);
               case FLT:
                  Integer var17 = var2._fcmp(var3);
                  return var17 != null && var17 < 0 ? ajr.pC(1L, 1) : ajr.pC(0L, 1);
               case FGT:
                  Integer var16 = var2._fcmp(var3);
                  return var16 != null && var16 > 0 ? ajr.pC(1L, 1) : ajr.pC(0L, 1);
               case FLE:
                  Integer var15 = var2._fcmp(var3);
                  return var15 != null && var15 <= 0 ? ajr.pC(1L, 1) : ajr.pC(0L, 1);
               case FGE:
                  Integer var14 = var2._fcmp(var3);
                  return var14 != null && var14 >= 0 ? ajr.pC(1L, 1) : ajr.pC(0L, 1);
               case FUN:
                  return var2._fcmp(var2) == null ? ajr.pC(1L, 1) : ajr.pC(0L, 1);
               case ADD:
                  var2 = var2._add(var3);
                  break;
               case SUB:
                  var2 = var2._sub(var3);
                  break;
               case MUL:
                  var2 = var2._mul(var3);
                  break;
               case DIV_S:
                  if (var3._signum() == 0) {
                     if (var1.isSoftFailMode()) {
                        return null;
                     }

                     throw new EvaluationException("Signed division by 0");
                  }

                  var2 = var2._div(var3);
                  break;
               case DIV_U:
                  if (var3._signum() == 0) {
                     if (var1.isSoftFailMode()) {
                        return null;
                     }

                     throw new EvaluationException("Unsigned division by 0");
                  }

                  var2 = var2._divU(var3);
                  break;
               case REM_S:
                  if (var3._signum() == 0) {
                     if (var1.isSoftFailMode()) {
                        return null;
                     }

                     throw new EvaluationException("Signed modulo by 0");
                  }

                  var2 = var2._rem(var3);
                  break;
               case REM_U:
                  if (var3._signum() == 0) {
                     if (var1.isSoftFailMode()) {
                        return null;
                     }

                     throw new EvaluationException("Unsigned modulo by 0");
                  }

                  var2 = var2._remU(var3);
                  break;
               case AND:
                  var2 = var2._and(var3);
                  break;
               case OR:
                  var2 = var2._or(var3);
                  break;
               case XOR:
                  var2 = var2._xor(var3);
                  break;
               case ROL:
                  var2 = var2._rol(EUtil.evalAsSaturatedPositiveInt(var3));
                  break;
               case ROR:
                  var2 = var2._ror(EUtil.evalAsSaturatedPositiveInt(var3));
                  break;
               case SAR:
                  var2 = var2._sar(EUtil.evalAsSaturatedPositiveInt(var3));
                  break;
               case SHL:
                  var2 = var2._shl(EUtil.evalAsSaturatedPositiveInt(var3));
                  break;
               case SHR:
                  var2 = var2._shr(EUtil.evalAsSaturatedPositiveInt(var3));
                  break;
               case NOT:
                  var2 = var2._not();
                  break;
               case POW:
                  var2 = var2._pow(EUtil.evalAsSaturatedPositiveInt(var3));
                  break;
               case ADD_SSAT:
               case ADD_USAT:
               case SUB_SSAT:
               case SUB_USAT:
               case MUL2_S:
               case MUL2_U:
               case DIV2_S:
               case DIV2_U:
               default:
                  var2 = null;
                  break;
               case FADD:
                  var2 = var2._fadd(var3);
                  break;
               case FSUB:
                  var2 = var2._fsub(var3);
                  break;
               case FMUL:
                  var2 = var2._fmul(var3);
                  break;
               case FDIV:
                  var2 = var2._fdiv(var3);
                  break;
               case CAST:
                  return var2.expand(this.kS);
               case CAST_S:
                  return var2.truncate(this.kS);
               case FP2FP:
                  int var13 = this.wS.getBitsize();
                  int var25 = this.getBitsize();
                  if (var13 == 32) {
                     if (var25 == 32) {
                        if (var2.getGroup() == IWildcardType.Group.FLOAT) {
                           return var2;
                        }

                        return ajt.A(var2.getValueAsSingleFloat());
                     }

                     if (var25 == 64) {
                        return aju.kS((double)var2.getValueAsSingleFloat());
                     }

                     if (var25 == 80) {
                        return ajv.kS((double)var2.getValueAsSingleFloat());
                     }
                  } else if (var13 == 64) {
                     if (var25 == 32) {
                        return ajt.A((float)var2.getValueAsDoubleFloat());
                     }

                     if (var25 == 64) {
                        if (var2.getGroup() == IWildcardType.Group.FLOAT) {
                           return var2;
                        }

                        return aju.kS(var2.getValueAsDoubleFloat());
                     }

                     if (var25 == 80) {
                        return ajv.kS(var2.getValueAsDoubleFloat());
                     }
                  } else if (var13 == 80) {
                     if (var25 == 32) {
                        return ajt.A((float)var2.getValueAsDoubleFloat());
                     }

                     if (var25 == 64) {
                        return aju.kS(var2.getValueAsDoubleFloat());
                     }

                     if (var25 == 80) {
                        if (var2.getGroup() == IWildcardType.Group.FLOAT) {
                           return var2;
                        }

                        return ajv.kS(var2.getValueAsDoubleFloat());
                     }
                  }

                  var2 = null;
                  break;
               case FP2INT:
                  int var12 = this.wS.getBitsize();
                  int var24 = this.getBitsize();
                  if (var12 == 32) {
                     float var33 = var2.getValueAsSingleFloat();
                     if (var24 == 32) {
                        if (!(var33 < -2.1474836E9F) && !(var33 > 2.1474836E9F)) {
                           return ajr.pC((int)var33, 32);
                        }

                        return null;
                     }

                     if (var24 == 64) {
                        if (!(var33 < -9.223372E18F) && !(var33 > 9.223372E18F)) {
                           return ajr.pC((long)var33, 64);
                        }

                        return null;
                     }
                  } else if (var12 == 64) {
                     double var34 = var2.getValueAsDoubleFloat();
                     if (var24 == 32) {
                        if (!(var34 < -2.1474836E9F) && !(var34 > 2.147483647E9)) {
                           return ajr.pC((int)var34, 32);
                        }

                        return null;
                     }

                     if (var24 == 64) {
                        if (!(var34 < -9.223372E18F) && !(var34 > 9.223372E18F)) {
                           return ajr.pC((long)var34, 64);
                        }

                        return null;
                     }
                  }

                  var2 = null;
                  break;
               case INT2FP:
               case UINT2FP:
                  int var11 = this.wS.getBitsize();
                  int var23 = this.getBitsize();
                  BigInteger var30 = var2.getValue();
                  if (var11 == 32) {
                     var30 = var30.and(BigInteger.valueOf(-1L));
                     if (this.A == OperationType.UINT2FP) {
                        var30 = new BigInteger(1, var30.toByteArray());
                     }

                     if (var23 == 32) {
                        return ajt.A(var30.floatValue());
                     }

                     if (var23 == 64) {
                        return aju.kS(var30.doubleValue());
                     }
                  } else if (var11 == 64) {
                     var30 = var30.and(BigInteger.valueOf(-1L));
                     if (this.A == OperationType.UINT2FP) {
                        var30 = new BigInteger(1, var30.toByteArray());
                     }

                     if (var23 == 32) {
                        return ajt.A(var30.floatValue());
                     }

                     if (var23 == 64) {
                        return aju.kS(var30.doubleValue());
                     }
                  }

                  var2 = null;
                  break;
               case FP2UINT:
                  int var4 = this.wS.getBitsize();
                  int var5 = this.getBitsize();
                  if (var4 == 32) {
                     float var6 = var2.getValueAsSingleFloat();
                     if (var5 == 32) {
                        if (!(var6 < 0.0F) && !(var6 > 4.2949673E9F)) {
                           return ajr.pC((long)var6, 32);
                        }

                        return null;
                     }

                     if (var5 == 64) {
                        if (!(var6 < 0.0F) && !(var6 > -1.0F)) {
                           return ajr.pC((long)var6, 64);
                        }

                        return null;
                     }
                  } else if (var4 == 64) {
                     double var29 = var2.getValueAsDoubleFloat();
                     if (var5 == 32) {
                        if (!(var29 < 0.0) && !(var29 > 4.294967295E9)) {
                           return ajr.pC((long)var29, 32);
                        }

                        return null;
                     }

                     if (var5 == 64) {
                        if (!(var29 < 0.0) && !(var29 > -1.0)) {
                           return ajr.pC((long)var29, 64);
                        }

                        return null;
                     }
                  }

                  var2 = null;
            }

            if (var2 == null) {
               if (var1.isSoftFailMode()) {
                  return null;
               } else {
                  throw new EvaluationException("Evaluation cannot be done");
               }
            } else {
               return var2;
            }
         }
      }
   }

   @Override
   public ICElement generateC(IERoutineContext var1, ICMethod var2) {
      return this.pC(var1, var2, true);
   }

   public ICElement pC(IERoutineContext var1, ICMethod var2, boolean var3) {
      IWildcardTypeManager var4 = var1.getWildcardTypeManager();
      if (var3) {
         IWildcardType var5 = this.getSafeType(var4);
         if (var5.isPointer() && var5.getPointedBitsize() != 0) {
            boolean var6 = false;
            if ((this.A == OperationType.ADD || this.A == OperationType.SUB)
               && this.UT instanceof ajr
               && this.wS.getType() != null
               && this.wS.getType().isPointer()) {
               var6 = true;
            }

            if (this.isCustomOperation()) {
               var6 = true;
            }

            if (!var6) {
               alo var7 = new alo(var1, var5.getPointedBitsize(), this, null);
               IEVar var8 = var7.pC(var2);
               if (var8 != null) {
                  return var8.generateC(var1, var2);
               }
            }
         }
      }

      ICOperatorFactory var26 = var2.getOperatorFactory();
      ICTypeFactory var27 = var2.getTypeFactory();
      ICElementFactory var28 = var2.getElementFactory();
      if (this.isCustomOperation()) {
         ICOperator var35 = var26.createCustomOperator(this.getOperationName(), this.getCountOfOperands());
         ArrayList var39 = new ArrayList(this.getCountOfOperands());

         for (IEGeneric var45 : this.getOperands()) {
            ICExpression var48 = (ICExpression)var45.generateC(var1, var2);
            var39.add(var48);
         }

         return var28.createOperation(var35, var39);
      } else if (this.A.isConversion()) {
         switch (this.A) {
            case CAST:
            case CAST_S:
               ICExpression var32 = null;
               boolean var38 = false;
               IWildcardType var42 = this.safelyType(var4);
               this.wS = this.pC(this.wS);
               IWildcardType var44 = this.wS.safelyType(var4);
               if (!var42.isResolved()) {
                  if (this.kS == 1) {
                     var38 = true;
                  }

                  IWildcardType.Group var46 = this.A == OperationType.CAST_S ? IWildcardType.Group.INTEGER : IWildcardType.Group.UINT;
                  IWildcardType var49 = var4.createWithEffectiveBitsize(this.kS).updateGroup(var46);
                  if (var42.isLessSpecializedThan(var49)) {
                     var42 = var49;
                  }
               }

               if (var42.getBitsize() > var44.getBitsize()) {
                  if (this.A == OperationType.CAST_S) {
                     if (var44.isSigned()) {
                        var38 = true;
                     }
                  } else if (this.A == OperationType.CAST && var44.isUnsigned()) {
                     var38 = true;
                  }
               }

               IWildcardType var47 = var42.resolveA();
               if (!var47.isResolved() || var47.getBitsize() != var42.getBitsize()) {
                  if (this.kS < this.wS.getBitsize()) {
                     IEOperation var50 = var1.createOperation(OperationType.AND, this.wS, EUtil.mask(this.wS.getBitsize(), this.kS));
                     var32 = (ICExpression)var50.generateC(var1, var2);
                     if (var47.equals(this.wS.getSafeType(var4).resolveA())) {
                        var47 = null;
                     }
                  } else if (this.kS > this.wS.getBitsize() && this.A == OperationType.CAST_S) {
                     var32 = (ICExpression)this.wS.generateC(var1, var2);
                     ICOperation var34 = var28.createCast(var27.create(var47), var32);
                     ICExpression var51 = (ICExpression)EUtil.mask(var47.getBitsize(), this.kS).generateC(var1, var2);
                     return var28.createOperation(COperatorType.AND, var34, var51);
                  }
               }

               if (var32 == null) {
                  var32 = (ICExpression)this.wS.generateC(var1, var2);
               }

               if (var47 != null && !var38) {
                  var32 = var28.createCast(var27.create(var47), var32);
               }

               return var32;
            case FP2FP:
            case INT2FP:
            case UINT2FP:
               int var37 = this.getSafeType(var4).getBitsize();
               IWildcardType var31;
               if (var37 == 32) {
                  var31 = var4.create("float");
               } else {
                  var31 = var4.create("double");
               }

               Object var41 = (ICExpression)this.wS.generateC(var1, var2);
               if (this.wS.getSafeType(var4).getGroup() != IWildcardType.Group.FLOAT) {
                  var41 = var28.createCast(var27.create(var31), (ICExpression)var41);
               }

               return (ICElement)var41;
            case FP2INT:
            case FP2UINT:
               int var36 = this.getSafeType(var4).getBitsize();
               IWildcardType var30 = var4.createWithEffectiveBitsize(var36)
                  .updateGroup(this.A == OperationType.FP2UINT ? IWildcardType.Group.UINT : IWildcardType.Group.INTEGER)
                  .resolveA();
               Object var40 = (ICExpression)this.wS.generateC(var1, var2);
               if (!var30.equals(this.wS.getType())) {
                  var40 = var28.createCast(var27.create(var30), (ICExpression)var40);
               }

               return (ICElement)var40;
            default:
               throw new RuntimeException();
         }
      } else {
         ICOperator var29 = switch (this.A) {
            case LOG_AND -> var26.get(COperatorType.LOG_AND);
            case LOG_OR -> var26.get(COperatorType.LOG_OR);
            case LOG_NOT -> var26.get(COperatorType.LOG_NOT);
            case LOG_EQ, FEQ -> var26.get(COperatorType.EQ);
            case LOG_NEQ, FNE -> var26.get(COperatorType.NE);
            case GT_S, GT_U, FGT -> var26.get(COperatorType.GT);
            case GE_S, GE_U, FGE -> var26.get(COperatorType.GE);
            case LT_S, LT_U, FLT -> var26.get(COperatorType.LT);
            case LE_S, LE_U, FLE -> var26.get(COperatorType.LE);
            case PAR -> var26.createCustomOperator("__parity__", 1);
            case CARRY -> var26.createCustomOperator("__carry__", 2);
            default -> var26.createCustomOperator(this.getOperationName(), this.getCountOfOperands());
            case ADD, FADD -> var26.get(COperatorType.ADD);
            case SUB, FSUB -> var26.get(COperatorType.SUB);
            case MUL, FMUL -> var26.get(COperatorType.MUL);
            case DIV_S, DIV_U, FDIV -> var26.get(COperatorType.DIV);
            case REM_S, REM_U -> var26.get(COperatorType.REM);
            case AND -> var26.get(COperatorType.AND);
            case OR -> var26.get(COperatorType.OR);
            case XOR -> var26.get(COperatorType.XOR);
            case ROL -> var26.createCustomOperator("__rol__", 2);
            case ROR -> var26.createCustomOperator("__ror__", 2);
            case SAR -> var26.get(COperatorType.SHR);
            case SHL -> var26.get(COperatorType.SHL);
            case SHR -> var26.get(COperatorType.USHR);
            case NOT -> var26.get(COperatorType.NOT);
            case POW -> var26.createCustomOperator("__pow__", 2);
         };
         if (var29 == null) {
            throw new RuntimeException("Cannot convert IR operator: " + this.A);
         } else {
            IWildcardType var9 = var4.create("char");
            IWildcardType var10 = var4.create("char*");
            IWildcardType var11 = var4.create("int");
            ICType var12 = var27.create(var10);
            ICType var13 = var27.create(var4.createWithSlotcount(1).updateGroup(IWildcardType.Group.INTEGER));
            IWildcardType var14 = this.wS == null ? null : this.wS.getSafeType(var4).resolveA();
            IWildcardType var15 = this.UT == null ? null : this.UT.getSafeType(var4).resolveA();
            IWildcardType var16 = this.getType();
            IWildcardType var17 = var14;
            if (var15 != null && var15.isPointer() && !var14.isPointer()) {
               var17 = var15;
            }

            if (this.A.isLogical()) {
               var17 = var11;
               if (var16 != null && !var16.isDefined()) {
                  var16 = var16.updateMaxBitsize(var11.getBitsize());
               }
            }

            IWildcardType var18 = null;
            Object var19 = this.wS == null ? null : (ICExpression)this.wS.generateC(var1, var2);
            Object var20 = this.UT == null ? null : (ICExpression)this.UT.generateC(var1, var2);
            IWildcardType.Group var21 = null;
            switch (this.A) {
               case GT_U:
               case GE_U:
               case LT_U:
               case LE_U:
               case DIV_U:
               case REM_U:
                  var21 = IWildcardType.Group.UINT;
               case GT_S:
               case GE_S:
               case LT_S:
               case LE_S:
               case DIV_S:
               case REM_S:
                  if (var21 == null) {
                     var21 = IWildcardType.Group.INTEGER;
                  }

                  if (!this.A.isLogical()
                     || var21 != IWildcardType.Group.UINT
                     || !var14.isPointer()
                     || !var15.isPointer()
                     || !var14.isResolved()
                     || !var15.isResolved()
                     || !TypeUtil.same(var14.getNativeType(), var15.getNativeType())) {
                     IWildcardType var22 = this.wS.getSafeType(var4);
                     IWildcardType.Group var23 = var22.getGroup();
                     if (var23 != null && var23 != var21 && !(var19 instanceof ICConstant)) {
                        ICType var24 = var27.create(var4.createWithEffectiveBitsize(var22.getBitsize()).updateGroup(var21));
                        var19 = var28.createCast(var24, (ICExpression)var19);
                     }

                     var22 = this.UT.getSafeType(var4);
                     var23 = var22.getGroup();
                     if (var23 != null && var23 != var21 && !(var20 instanceof ICConstant)) {
                        ICType var58 = var27.create(var4.createWithEffectiveBitsize(var22.getBitsize()).updateGroup(var21));
                        var20 = var28.createCast(var58, (ICExpression)var20);
                     }
                  }
               case PAR:
               case CARRY:
               case FEQ:
               case FNE:
               case FLT:
               case FGT:
               case FLE:
               case FGE:
               case FUN:
               case ADD:
               case SUB:
               case MUL:
               default:
                  boolean var53 = false;
                  if (this.A == OperationType.ADD || this.A == OperationType.SUB) {
                     if (var14.isPointer()) {
                        int var55 = var14.getPointedSize();
                        if (var55 > 1) {
                           if (this.UT instanceof ajr) {
                              int var59 = (int)((ajr)this.UT).getValueAsLong();
                              if (var59 % var55 == 0) {
                                 var20 = var2.getGlobalContext().getConstantFactory().createInt32(var59 / var55);
                              } else if (var16 != null && var16.isPointer() && var16.getPointedBitsize() == var9.getBitsize()) {
                                 var19 = var28.createCast(var27.create(var16), (ICExpression)var19);
                                 var18 = var16;
                              } else {
                                 var19 = var28.createCast(var12, (ICExpression)var19);
                                 var18 = var10;
                              }

                              var53 = true;
                           }
                        } else if (var55 == 1) {
                           var53 = true;
                        }
                     } else if (var15.isPointer()) {
                        int var56 = var15.getPointedSize();
                        if (var56 <= 1 && var56 == 1) {
                           var53 = true;
                        }
                     }
                  }

                  if (!var53 && !this.A.isLogical()) {
                     if (var14.isPointer()) {
                        var19 = var28.createOperation(var26.createCastOperator(var13), (ICExpression)var19);
                        var18 = var11;
                     }

                     if (var15 != null && var15.isPointer()) {
                        var20 = var28.createOperation(var26.createCastOperator(var13), (ICExpression)var20);
                        var18 = var11;
                     }
                  }

                  ICOperation var57 = var28.createOperation(var29, (ICExpression)var19, (ICExpression)var20);
                  IWildcardType var60 = null;
                  if (var16 != null && !var16.isUndefined()) {
                     if (var18 != null) {
                        var17 = var18;
                     }

                     if (!var16.resolveA().isEquivalent(var17)) {
                        var60 = var16;
                     }
                  } else if (var18 != null && !var18.equals(var17)) {
                     var60 = var17;
                  }

                  if (var60 != null) {
                     ICOperator var25 = var26.createCastOperator(var27.create(var60));
                     var57 = var28.createOperation(var25, var57);
                  }

                  return var57;
            }
         }
      }
   }

   @Override
   public void pC(akz var1) {
      if (this.isCustomOperation()) {
         var1.append(this.sY.getName());
         var1.paren();
         int var2 = 0;

         for (IEGeneric var4 : this.getOperands()) {
            if (var2 > 0) {
               var1.append(", ");
            }

            var1.pC(var4);
            var2++;
         }

         var1.parenClose();
      } else if (this.UT == null) {
         var1.append(this.A.toString());
         if (this.A.isConversion()) {
            var1.append("_" + this.kS);
         }

         var1.paren();
         var1.pC(this.wS);
         var1.parenClose();
      } else {
         var1.paren();
         var1.pC(this.wS);
         var1.append(" ");
         var1.append(this.A.toString());
         var1.append(" ");
         var1.pC(this.UT);
         var1.parenClose();
      }

      var1.A(this);
   }
}
