package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.IJavaAnnotation;
import com.pnfsoftware.jeb.core.units.code.java.IJavaAnnotationElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaArrayElt;
import com.pnfsoftware.jeb.core.units.code.java.IJavaAssignment;
import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaBreak;
import com.pnfsoftware.jeb.core.units.code.java.IJavaCall;
import com.pnfsoftware.jeb.core.units.code.java.IJavaConditionalExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaConstant;
import com.pnfsoftware.jeb.core.units.code.java.IJavaContinue;
import com.pnfsoftware.jeb.core.units.code.java.IJavaDecompilableElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaDefinition;
import com.pnfsoftware.jeb.core.units.code.java.IJavaDoWhile;
import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaField;
import com.pnfsoftware.jeb.core.units.code.java.IJavaFor;
import com.pnfsoftware.jeb.core.units.code.java.IJavaForEach;
import com.pnfsoftware.jeb.core.units.code.java.IJavaGlobalContext;
import com.pnfsoftware.jeb.core.units.code.java.IJavaGoto;
import com.pnfsoftware.jeb.core.units.code.java.IJavaIf;
import com.pnfsoftware.jeb.core.units.code.java.IJavaInstanceField;
import com.pnfsoftware.jeb.core.units.code.java.IJavaLabel;
import com.pnfsoftware.jeb.core.units.code.java.IJavaLeftExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaMethod;
import com.pnfsoftware.jeb.core.units.code.java.IJavaMonitor;
import com.pnfsoftware.jeb.core.units.code.java.IJavaNew;
import com.pnfsoftware.jeb.core.units.code.java.IJavaNewArray;
import com.pnfsoftware.jeb.core.units.code.java.IJavaOperation;
import com.pnfsoftware.jeb.core.units.code.java.IJavaOperator;
import com.pnfsoftware.jeb.core.units.code.java.IJavaPredicate;
import com.pnfsoftware.jeb.core.units.code.java.IJavaReturn;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStaticField;
import com.pnfsoftware.jeb.core.units.code.java.IJavaSwitch;
import com.pnfsoftware.jeb.core.units.code.java.IJavaSynchronizedBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaThrow;
import com.pnfsoftware.jeb.core.units.code.java.IJavaTry;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.core.units.code.java.IJavaTypeReference;
import com.pnfsoftware.jeb.core.units.code.java.IJavaWhile;
import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;
import com.pnfsoftware.jeb.core.util.DecompilerHelper;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.util.ArrayList;
import java.util.List;

@Ser
public class blz implements IJavaGlobalContext {
   @SerId(1)
   public com.pnfsoftware.jeb.corei.parsers.dex.bK q;
   @SerId(2)
   public cis RF;
   @SerId(3)
   public cio xK;
   @SerId(4)
   public bmv Dw;
   @SerId(5)
   public blw Uv;
   @SerId(6)
   public bma oW;
   @SerId(7)
   public bly gO;
   @SerId(8)
   public bmh nf;
   @SerId(9)
   public com.pnfsoftware.jeb.corei.parsers.dexdec.ej gP;
   @SerTransient
   public boolean za;

   @SerCustomInitPostGraph
   private void gP() {
      if (this.gP == null) {
         this.gP = (com.pnfsoftware.jeb.corei.parsers.dexdec.ej)DecompilerHelper.getDecompiler(this.q, false);
      }

      if (this.gP != null && this.RF.q() == null) {
         this.RF.q(this.gP);
      }
   }

   public blz(com.pnfsoftware.jeb.corei.parsers.dexdec.ej var1) {
      this.gP = var1;
      this.q = var1.TX();
      this.RF = new cis();
      this.RF.q(var1);
      this.xK = new cio(this.RF);
      this.Dw = new bmv(this.RF);
      this.Uv = new blw(var1, this.RF, this.Dw);
      this.oW = new bma(var1, this.RF, this.Dw, this.Uv);
      this.gO = new bly(var1, this.RF, this.Dw, this.Uv);
      this.Uv.q(this.oW);
      this.nf = new bmh(var1, this.RF, this.Dw, this.oW, this.gO, this);
   }

   public com.pnfsoftware.jeb.corei.parsers.dex.bK q() {
      return this.q;
   }

   public com.pnfsoftware.jeb.corei.parsers.dexdec.ej RF() {
      return this.gP;
   }

   public cis xK() {
      return this.RF;
   }

   public cio Dw() {
      return this.xK;
   }

   public bmv Uv() {
      return this.Dw;
   }

   public blw oW() {
      return this.Uv;
   }

   public bma gO() {
      return this.oW;
   }

   public bly nf() {
      return this.gO;
   }

   private bmj q(bmj var1) {
      var1.q(this.gP);
      return var1;
   }

   @Override
   public IJavaMethod createMethodReference(String var1) {
      return this.createMethodReference(var1, null);
   }

   @Override
   public IJavaMethod createMethodReference(String var1, Boolean var2) {
      if (var2 != null) {
         this.gP.q(var1, var2);
      }

      return this.oW.RF(var1);
   }

   @Override
   public IJavaField createFieldReference(String var1) {
      return this.createFieldReference(var1, null);
   }

   @Override
   public IJavaField createFieldReference(String var1, Boolean var2) {
      if (var2 != null) {
         this.gP.q(var1, var2);
      }

      return this.gO.RF(var1);
   }

   @Override
   public IJavaArrayElt createArrayElt(IJavaExpression var1, IJavaExpression var2) {
      bmm var3 = new bmm(var1, var2);
      return (IJavaArrayElt)this.q(var3);
   }

   @Override
   public IJavaAssignment createAssignment(IJavaLeftExpression var1, IJavaExpression var2) {
      return new bmn(var1, var2);
   }

   @Override
   public IJavaBreak createBreak(IJavaLabel var1) {
      return new bmp((bnp)var1);
   }

   @Override
   public IJavaBlock createBlock() {
      return new bmo();
   }

   @Override
   public IJavaBlock createBlock(IJavaStatement var1) {
      return new bmo(var1);
   }

   @Override
   public IJavaCall createCall(IJavaMethod var1, int var2) {
      return this.createCall(var1.getSignature(), var2, null);
   }

   @Override
   public IJavaCall createCall(String var1, int var2) {
      return this.createCall(var1, var2, null);
   }

   @Override
   public IJavaCall createCall(IJavaMethod var1, int var2, List var3) {
      return this.createCall(var1.getSignature(), var2, var3);
   }

   @Override
   public IJavaCall createCall(String var1, int var2, List var3) {
      bmq var4 = new bmq(var1, var2);
      if (var3 != null) {
         for (IJavaExpression var6 : var3) {
            var4.addArgument(var6);
         }
      }

      return (IJavaCall)this.q(var4);
   }

   @Override
   public IJavaTypeReference createTypeReference(IJavaType var1) {
      return new boe((ciq)var1);
   }

   @Override
   public IJavaConditionalExpression createConditionalExpression(IJavaExpression var1, IJavaExpression var2, IJavaExpression var3) {
      return new bmt(var1, var2, var3);
   }

   @Override
   public IJavaContinue createContinue(IJavaLabel var1) {
      return new bmw(var1);
   }

   @Override
   public IJavaDoWhile createDoWhile(IJavaBlock var1, IJavaPredicate var2) {
      return new bmy(var1, var2);
   }

   @Override
   public IJavaOperation createOperation(IJavaExpression var1, JavaOperatorType var2, IJavaExpression var3) {
      return this.createOperation(var1, this.xK.get(var2), var3);
   }

   @Override
   public IJavaOperation createOperation(IJavaExpression var1, IJavaOperator var2, IJavaExpression var3) {
      return new bnu(var1, var2, var3);
   }

   @Override
   public IJavaOperation createCastOperation(IJavaType var1, IJavaExpression var2) {
      return new bnu(null, this.xK.createCastOperator(var1), var2);
   }

   @Override
   public IJavaFor createFor(IJavaStatement var1, IJavaPredicate var2, IJavaStatement var3, IJavaBlock var4) {
      return new bna((bml)var1, (bnv)var2, (bml)var3, (bmo)var4);
   }

   @Override
   public IJavaForEach createForEach(IJavaDefinition var1, IJavaExpression var2, IJavaBlock var3) {
      return new bmz(var1, var2, var3);
   }

   @Override
   public IJavaGoto createGoto(IJavaLabel var1) {
      return new bnb((bnp)var1);
   }

   @Override
   public IJavaIf createIf(IJavaPredicate var1, IJavaBlock var2) {
      return new bne((bnv)var1, (bmo)var2);
   }

   @Override
   public IJavaNew createNew(IJavaType var1, IJavaMethod var2) {
      return this.createNew(var1, var2, null);
   }

   @Override
   public IJavaNew createNew(IJavaType var1, IJavaMethod var2, List var3) {
      return this.createNew(var1, var2.getSignature(), var3);
   }

   @Override
   public IJavaNew createNew(IJavaType var1, String var2, List var3) {
      bns var4 = new bns(var2, (ciq)var1);
      if (var3 != null) {
         for (IJavaExpression var6 : var3) {
            var4.q(var6);
         }
      }

      var4.q(this.gP);
      return var4;
   }

   @Override
   public IJavaNewArray createNewArray(IJavaType var1, IJavaExpression var2) {
      return new bnt(var1, var2);
   }

   @Override
   public IJavaNewArray createNewArray(IJavaType var1, boolean var2, List var3) {
      ArrayList var4 = new ArrayList();

      for (IJavaExpression var6 : var3) {
         var4.add(var6);
      }

      return new bnt((ciq)var1, var2, var4);
   }

   @Override
   public IJavaPredicate createPredicate(IJavaExpression var1) {
      return new bnv(var1);
   }

   @Override
   public IJavaReturn createReturn() {
      return new bnx(null);
   }

   @Override
   public IJavaReturn createReturn(IJavaExpression var1) {
      return new bnx(var1);
   }

   @Override
   public IJavaStaticField createStaticField(IJavaType var1, IJavaField var2) {
      return this.createStaticField(var1, var2.getSignature());
   }

   @Override
   public IJavaStaticField createStaticField(IJavaType var1, String var2) {
      bny var3 = new bny(var2, var1);
      var3.q(this.gP);
      return var3;
   }

   @Override
   public IJavaInstanceField createInstanceField(IJavaExpression var1, IJavaField var2) {
      return this.createInstanceField(var1, var2.getSignature());
   }

   @Override
   public IJavaInstanceField createInstanceField(IJavaExpression var1, String var2) {
      bnf var3 = new bnf(var2, var1);
      var3.q(this.gP);
      return var3;
   }

   @Override
   public IJavaSwitch createSwitch(IJavaExpression var1) {
      return new bnz(var1);
   }

   @Override
   public IJavaSwitch createSwitch(IJavaExpression var1, int var2) {
      return new bnz(var1, var2);
   }

   @Override
   public IJavaTry createTry(IJavaBlock var1) {
      return new bod(var1);
   }

   @Override
   public IJavaMonitor createMonitor(boolean var1, IJavaExpression var2) {
      return new bnr(var1, var2);
   }

   @Override
   public IJavaSynchronizedBlock createSynchronizedBlock(IJavaExpression var1, IJavaBlock var2) {
      return new bob(var1, var2);
   }

   @Override
   public IJavaThrow createThrow(IJavaExpression var1) {
      return new boc(var1);
   }

   @Override
   public IJavaWhile createWhile(IJavaPredicate var1, IJavaBlock var2) {
      return new bof(var1, var2);
   }

   @Override
   public IJavaAnnotation createAnnotation(IJavaType var1, List var2) {
      return new bng(var1, var2);
   }

   @Override
   public IJavaAnnotationElement createAnnotationElement(IJavaConstant var1, IJavaExpression var2) {
      return new bnh(var1, var2);
   }

   public blp q(IJavaDecompilableElement var1, boolean var2, boolean var3, boolean var4) {
      return this.gP.q(var1, null, null, var2, var3, var4);
   }
}
