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
public class agc implements ICElementFactory {
   @SerId(1)
   ICGlobalContext q;

   public agc(ICGlobalContext var1) {
      this.q = var1;
   }

   @Override
   public ICDecl createDecl(ICIdentifier var1) {
      return new afz(var1);
   }

   @Override
   public ICArrayElement createArrayElement(ICExpression var1, ICExpression var2) {
      return new afi(var1, var2);
   }

   @Override
   public ICAssignment createAssignment(ICLeftExpression var1, ICExpression var2) {
      return new afj(var1, var2);
   }

   @Override
   public ICBlock createBlock() {
      return new afk();
   }

   @Override
   public ICBlock createBlock(ICStatement var1) {
      return new afk(var1);
   }

   @Override
   public ICBlock createBlock(ICStatement... var1) {
      return new afk(var1);
   }

   @Override
   public ICBreak createBreak() {
      return this.createBreak(null);
   }

   @Override
   public ICBreak createBreak(ICLabel var1) {
      return new afl(var1);
   }

   @Override
   public ICContinue createContinue() {
      return this.createContinue(null);
   }

   @Override
   public ICContinue createContinue(ICLabel var1) {
      return new afx(var1);
   }

   @Override
   public ICCall createCall(ICMethod var1, List var2) {
      afm var3 = new afm(var1.getAddress(), var2);
      var3.q(this.q);
      return var3;
   }

   @Override
   public ICCall createCall(ICExpression var1, List var2, List var3) {
      List var4 = null;
      if (var3 != null) {
         var4 = (List)var3.stream().map(var0 -> var0.getAddress()).collect(Collectors.toCollection(ArrayList::new));
      }

      afm var5 = new afm(var1, var2, var4);
      var5.q(this.q);
      return var5;
   }

   @Override
   public ICDoWhileStm createDoWhileStm(ICBlock var1, ICPredicate var2) {
      return new agb(var1, var2);
   }

   @Override
   public ICForStm createForLoop(ICStatement var1, ICPredicate var2, ICStatement var3, ICBlock var4) {
      return new agf(var1, var2, var3, var4);
   }

   @Override
   public ICGoto createGoto(ICLabel var1) {
      return new agg(var1);
   }

   @Override
   public ICIfStm createIfStm(ICPredicate var1, ICBlock var2) {
      return new agk(var1, var2);
   }

   @Override
   public ICIfStm createIfStm(ICPredicate var1, ICStatement var2) {
      return new agk(var1, var2);
   }

   @Override
   public ICIfStm createIfStm(ICPredicate var1, ICStatement var2, ICStatement var3) {
      agk var4 = new agk(var1, var2);
      var4.setDefaultBlock(new afk(var3));
      return var4;
   }

   @Override
   public ICInstanceField createInstanceField(ICField var1, ICExpression var2, boolean var3) {
      agl var4 = new agl(var1.getAddress(), var2, var3);
      var4.q(this.q);
      return var4;
   }

   @Override
   public ICJumpFar createJumpFar(ICExpression var1) {
      return new agm(var1);
   }

   @Override
   public ICCustomStatement createNativeStatement(long var1) {
      return new afy(var1);
   }

   @Override
   public ICOperation createOperation(ICOperator var1, ICExpression var2) {
      return new agq(var1, var2);
   }

   @Override
   public ICOperation createOperation(ICOperator var1, ICExpression var2, ICExpression var3) {
      return new agq(var1, var2, var3);
   }

   @Override
   public ICOperation createOperation(ICOperator var1, ICExpression var2, ICExpression var3, ICExpression var4) {
      return new agq(var1, var2, var3, var4);
   }

   @Override
   public ICOperation createOperation(COperatorType var1, ICExpression var2) {
      return new agq(this.q.getOperatorFactory().get(var1), var2);
   }

   @Override
   public ICOperation createOperation(COperatorType var1, ICExpression var2, ICExpression var3) {
      return new agq(this.q.getOperatorFactory().get(var1), var2, var3);
   }

   @Override
   public ICOperation createOperation(COperatorType var1, ICExpression var2, ICExpression var3, ICExpression var4) {
      return new agq(this.q.getOperatorFactory().get(var1), var2, var3, var4);
   }

   @Override
   public ICOperation createCast(ICType var1, ICExpression var2) {
      return new agq(this.q.getOperatorFactory().createCastOperator(var1), var2);
   }

   @Override
   public ICPredicate createPredicate(ICExpression var1) {
      return new agv(var1);
   }

   @Override
   public ICReturn createReturn() {
      return this.createReturn(null);
   }

   @Override
   public ICReturn createReturn(ICExpression var1) {
      return new agw(var1);
   }

   @Override
   public ICSwitchStm createSwitchStm(ICExpression var1) {
      return new agy(var1);
   }

   @Override
   public ICTuple createTuple(List var1) {
      return new ahb(var1);
   }

   @Override
   public ICWhileStm createWhileStm(ICPredicate var1, ICBlock var2) {
      return new ahe(var1, var2);
   }

   @Override
   public ICThrow createThrow(ICExpression var1) {
      return new aha(var1);
   }
}
