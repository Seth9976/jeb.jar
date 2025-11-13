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
public class bic implements IJavaGlobalContext {
   @SerId(1)
   public com.pnfsoftware.jeb.corei.parsers.dex.vi pC;
   @SerId(2)
   public cdk A;
   @SerId(3)
   public cdg kS;
   @SerId(4)
   public biy wS;
   @SerId(5)
   public bhz UT;
   @SerId(6)
   public bid E;
   @SerId(7)
   public bib sY;
   @SerId(8)
   public bik ys;
   @SerId(9)
   public com.pnfsoftware.jeb.corei.parsers.dexdec.Ws ld;
   @SerTransient
   public boolean gp;

   @SerCustomInitPostGraph
   private void ys() {
      if (this.ld == null) {
         this.ld = (com.pnfsoftware.jeb.corei.parsers.dexdec.Ws)DecompilerHelper.getDecompiler(this.pC, false);
      }

      if (this.ld != null && this.A.pC() == null) {
         this.A.pC(this.ld);
      }
   }

   public bic(com.pnfsoftware.jeb.corei.parsers.dexdec.Ws var1) {
      this.ld = var1;
      this.pC = var1.ld();
      this.A = new cdk();
      this.A.pC(var1);
      this.kS = new cdg(this.A);
      this.wS = new biy(this.A);
      this.UT = new bhz(var1, this.A, this.wS);
      this.E = new bid(var1, this.A, this.wS, this.UT);
      this.sY = new bib(var1, this.A, this.wS, this.UT);
      this.UT.pC(this.E);
      this.ys = new bik(var1, this.A, this.wS, this.E, this.sY, this);
   }

   public com.pnfsoftware.jeb.corei.parsers.dexdec.Ws pC() {
      return this.ld;
   }

   public cdk A() {
      return this.A;
   }

   public cdg kS() {
      return this.kS;
   }

   public biy wS() {
      return this.wS;
   }

   public bhz UT() {
      return this.UT;
   }

   public bid E() {
      return this.E;
   }

   public bib sY() {
      return this.sY;
   }

   private bim pC(bim var1) {
      var1.pC(this.ld);
      return var1;
   }

   @Override
   public IJavaMethod createMethodReference(String var1) {
      return this.createMethodReference(var1, null);
   }

   @Override
   public IJavaMethod createMethodReference(String var1, Boolean var2) {
      if (var2 != null) {
         this.ld.pC(var1, var2);
      }

      return this.E.A(var1);
   }

   @Override
   public IJavaField createFieldReference(String var1) {
      return this.createFieldReference(var1, null);
   }

   @Override
   public IJavaField createFieldReference(String var1, Boolean var2) {
      if (var2 != null) {
         this.ld.pC(var1, var2);
      }

      return this.sY.A(var1);
   }

   @Override
   public IJavaArrayElt createArrayElt(IJavaExpression var1, IJavaExpression var2) {
      bip var3 = new bip(var1, var2);
      return (IJavaArrayElt)this.pC(var3);
   }

   @Override
   public IJavaAssignment createAssignment(IJavaLeftExpression var1, IJavaExpression var2) {
      return new biq(var1, var2);
   }

   @Override
   public IJavaBreak createBreak(IJavaLabel var1) {
      return new bis((bjs)var1);
   }

   @Override
   public IJavaBlock createBlock() {
      return new bir();
   }

   @Override
   public IJavaBlock createBlock(IJavaStatement var1) {
      return new bir(var1);
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
      bit var4 = new bit(var1, var2);
      if (var3 != null) {
         for (IJavaExpression var6 : var3) {
            var4.addArgument(var6);
         }
      }

      return (IJavaCall)this.pC(var4);
   }

   @Override
   public IJavaTypeReference createTypeReference(IJavaType var1) {
      return new bkh((cdi)var1);
   }

   @Override
   public IJavaConditionalExpression createConditionalExpression(IJavaExpression var1, IJavaExpression var2, IJavaExpression var3) {
      return new biw(var1, var2, var3);
   }

   @Override
   public IJavaContinue createContinue(IJavaLabel var1) {
      return new biz(var1);
   }

   @Override
   public IJavaDoWhile createDoWhile(IJavaBlock var1, IJavaPredicate var2) {
      return new bjb(var1, var2);
   }

   @Override
   public IJavaOperation createOperation(IJavaExpression var1, JavaOperatorType var2, IJavaExpression var3) {
      return this.createOperation(var1, this.kS.get(var2), var3);
   }

   @Override
   public IJavaOperation createOperation(IJavaExpression var1, IJavaOperator var2, IJavaExpression var3) {
      return new bjx(var1, var2, var3);
   }

   @Override
   public IJavaOperation createCastOperation(IJavaType var1, IJavaExpression var2) {
      return new bjx(null, this.kS.createCastOperator(var1), var2);
   }

   @Override
   public IJavaFor createFor(IJavaStatement var1, IJavaPredicate var2, IJavaStatement var3, IJavaBlock var4) {
      return new bjd((bio)var1, (bjy)var2, (bio)var3, (bir)var4);
   }

   @Override
   public IJavaForEach createForEach(IJavaDefinition var1, IJavaExpression var2, IJavaBlock var3) {
      return new bjc(var1, var2, var3);
   }

   @Override
   public IJavaGoto createGoto(IJavaLabel var1) {
      return new bje((bjs)var1);
   }

   @Override
   public IJavaIf createIf(IJavaPredicate var1, IJavaBlock var2) {
      return new bjh((bjy)var1, (bir)var2);
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
      bjv var4 = new bjv(var2, (cdi)var1);
      if (var3 != null) {
         for (IJavaExpression var6 : var3) {
            var4.pC(var6);
         }
      }

      var4.pC(this.ld);
      return var4;
   }

   @Override
   public IJavaNewArray createNewArray(IJavaType var1, IJavaExpression var2) {
      return new bjw(var1, var2);
   }

   @Override
   public IJavaNewArray createNewArray(IJavaType var1, boolean var2, List var3) {
      ArrayList var4 = new ArrayList();

      for (IJavaExpression var6 : var3) {
         var4.add(var6);
      }

      return new bjw((cdi)var1, var2, var4);
   }

   @Override
   public IJavaPredicate createPredicate(IJavaExpression var1) {
      return new bjy(var1);
   }

   @Override
   public IJavaReturn createReturn() {
      return new bka(null);
   }

   @Override
   public IJavaReturn createReturn(IJavaExpression var1) {
      return new bka(var1);
   }

   @Override
   public IJavaStaticField createStaticField(IJavaType var1, IJavaField var2) {
      return this.createStaticField(var1, var2.getSignature());
   }

   @Override
   public IJavaStaticField createStaticField(IJavaType var1, String var2) {
      bkb var3 = new bkb(var2, var1);
      var3.pC(this.ld);
      return var3;
   }

   @Override
   public IJavaInstanceField createInstanceField(IJavaExpression var1, IJavaField var2) {
      return this.createInstanceField(var1, var2.getSignature());
   }

   @Override
   public IJavaInstanceField createInstanceField(IJavaExpression var1, String var2) {
      bji var3 = new bji(var2, var1);
      var3.pC(this.ld);
      return var3;
   }

   @Override
   public IJavaSwitch createSwitch(IJavaExpression var1) {
      return new bkc(var1);
   }

   @Override
   public IJavaSwitch createSwitch(IJavaExpression var1, int var2) {
      return new bkc(var1, var2);
   }

   @Override
   public IJavaTry createTry(IJavaBlock var1) {
      return new bkg(var1);
   }

   @Override
   public IJavaMonitor createMonitor(boolean var1, IJavaExpression var2) {
      return new bju(var1, var2);
   }

   @Override
   public IJavaSynchronizedBlock createSynchronizedBlock(IJavaExpression var1, IJavaBlock var2) {
      return new bke(var1, var2);
   }

   @Override
   public IJavaThrow createThrow(IJavaExpression var1) {
      return new bkf(var1);
   }

   @Override
   public IJavaWhile createWhile(IJavaPredicate var1, IJavaBlock var2) {
      return new bki(var1, var2);
   }

   @Override
   public IJavaAnnotation createAnnotation(IJavaType var1, List var2) {
      return new bjj(var1, var2);
   }

   @Override
   public IJavaAnnotationElement createAnnotationElement(IJavaConstant var1, IJavaExpression var2) {
      return new bjk(var1, var2);
   }

   public bhs pC(IJavaDecompilableElement var1, boolean var2, boolean var3, boolean var4) {
      return this.ld.pC(var1, null, null, var2, var3, var4);
   }
}
