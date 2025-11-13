package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COperatorType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICArrayElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICAssignment;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBreak;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICCall;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICContinue;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICCustomStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICDecl;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICDoWhileStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElementFactory;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICField;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICForStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICGlobalContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICGoto;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIdentifier;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIfStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICInstanceField;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICJumpFar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICLabel;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICLeftExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICOperator;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICPredicate;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICReturn;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICSwitchStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICThrow;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICTuple;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICWhileStm;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Ser
public class aej implements ICElementFactory {
   @SerId(1)
   ICGlobalContext pC;

   public aej(ICGlobalContext var1) {
      this.pC = var1;
   }

   @Override
   public ICDecl createDecl(ICIdentifier var1) {
      return new aeg(var1);
   }

   @Override
   public ICArrayElement createArrayElement(ICExpression var1, ICExpression var2) {
      return new adp(var1, var2);
   }

   @Override
   public ICAssignment createAssignment(ICLeftExpression var1, ICExpression var2) {
      return new adq(var1, var2);
   }

   @Override
   public ICBlock createBlock() {
      return new adr();
   }

   @Override
   public ICBlock createBlock(ICStatement var1) {
      return new adr(var1);
   }

   @Override
   public ICBlock createBlock(ICStatement... var1) {
      return new adr(var1);
   }

   @Override
   public ICBreak createBreak() {
      return this.createBreak(null);
   }

   @Override
   public ICBreak createBreak(ICLabel var1) {
      return new ads(var1);
   }

   @Override
   public ICContinue createContinue() {
      return this.createContinue(null);
   }

   @Override
   public ICContinue createContinue(ICLabel var1) {
      return new aee(var1);
   }

   @Override
   public ICCall createCall(ICMethod var1, List var2) {
      adt var3 = new adt(var1.getAddress(), var2);
      var3.pC(this.pC);
      return var3;
   }

   @Override
   public ICCall createCall(ICExpression var1, List var2, List var3) {
      List var4 = null;
      if (var3 != null) {
         var4 = (List)var3.stream().map(var0 -> var0.getAddress()).collect(Collectors.toCollection(ArrayList::new));
      }

      adt var5 = new adt(var1, var2, var4);
      var5.pC(this.pC);
      return var5;
   }

   @Override
   public ICDoWhileStm createDoWhileStm(ICBlock var1, ICPredicate var2) {
      return new aei(var1, var2);
   }

   @Override
   public ICForStm createForLoop(ICStatement var1, ICPredicate var2, ICStatement var3, ICBlock var4) {
      return new aem(var1, var2, var3, var4);
   }

   @Override
   public ICGoto createGoto(ICLabel var1) {
      return new aen(var1);
   }

   @Override
   public ICIfStm createIfStm(ICPredicate var1, ICBlock var2) {
      return new aer(var1, var2);
   }

   @Override
   public ICIfStm createIfStm(ICPredicate var1, ICStatement var2) {
      return new aer(var1, var2);
   }

   @Override
   public ICIfStm createIfStm(ICPredicate var1, ICStatement var2, ICStatement var3) {
      aer var4 = new aer(var1, var2);
      var4.setDefaultBlock(new adr(var3));
      return var4;
   }

   @Override
   public ICInstanceField createInstanceField(ICField var1, ICExpression var2, boolean var3) {
      aes var4 = new aes(var1.getAddress(), var2, var3);
      var4.pC(this.pC);
      return var4;
   }

   @Override
   public ICJumpFar createJumpFar(ICExpression var1) {
      return new aet(var1);
   }

   @Override
   public ICCustomStatement createNativeStatement(long var1) {
      return new aef(var1);
   }

   @Override
   public ICOperation createOperation(ICOperator var1, ICExpression var2) {
      return new aex(var1, var2);
   }

   @Override
   public ICOperation createOperation(ICOperator var1, ICExpression var2, ICExpression var3) {
      return new aex(var1, var2, var3);
   }

   @Override
   public ICOperation createOperation(ICOperator var1, ICExpression var2, ICExpression var3, ICExpression var4) {
      return new aex(var1, var2, var3, var4);
   }

   @Override
   public ICOperation createOperation(ICOperator var1, List var2) {
      if (var2.isEmpty()) {
         return new aex(var1);
      } else if (var2.size() == 1) {
         return new aex(var1, (ICExpression)var2.get(0));
      } else if (var2.size() == 2) {
         return new aex(var1, (ICExpression)var2.get(0), (ICExpression)var2.get(1));
      } else {
         return var2.size() == 3
            ? new aex(var1, (ICExpression)var2.get(0), (ICExpression)var2.get(1), (ICExpression)var2.get(2))
            : new aex(var1, (ICExpression)var2.get(0), (ICExpression)var2.get(1), (ICExpression)var2.get(2), new ArrayList(var2.subList(3, var2.size())));
      }
   }

   @Override
   public ICOperation createOperation(COperatorType var1, ICExpression var2) {
      return this.createOperation(this.pC.getOperatorFactory().get(var1), var2);
   }

   @Override
   public ICOperation createOperation(COperatorType var1, ICExpression var2, ICExpression var3) {
      return this.createOperation(this.pC.getOperatorFactory().get(var1), var2, var3);
   }

   @Override
   public ICOperation createOperation(COperatorType var1, ICExpression var2, ICExpression var3, ICExpression var4) {
      return this.createOperation(this.pC.getOperatorFactory().get(var1), var2, var3, var4);
   }

   @Override
   public ICOperation createOperation(COperatorType var1, List var2) {
      return this.createOperation(this.pC.getOperatorFactory().get(var1), var2);
   }

   @Override
   public ICOperation createCast(ICType var1, ICExpression var2) {
      return this.createOperation(this.pC.getOperatorFactory().createCastOperator(var1), var2);
   }

   @Override
   public ICPredicate createPredicate(ICExpression var1) {
      return new afc(var1);
   }

   @Override
   public ICReturn createReturn() {
      return this.createReturn(null);
   }

   @Override
   public ICReturn createReturn(ICExpression var1) {
      return new afd(var1);
   }

   @Override
   public ICSwitchStm createSwitchStm(ICExpression var1) {
      return new aff(var1);
   }

   @Override
   public ICTuple createTuple(List var1) {
      return new afi(var1);
   }

   @Override
   public ICWhileStm createWhileStm(ICPredicate var1, ICBlock var2) {
      return new afl(var1, var2);
   }

   @Override
   public ICThrow createThrow(ICExpression var1) {
      return new afh(var1);
   }
}
