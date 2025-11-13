package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.AbstractJOptimizer;
import com.pnfsoftware.jeb.core.units.code.java.IJavaArrayElt;
import com.pnfsoftware.jeb.core.units.code.java.IJavaAssignment;
import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaCall;
import com.pnfsoftware.jeb.core.units.code.java.IJavaClass;
import com.pnfsoftware.jeb.core.units.code.java.IJavaConstant;
import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaInstanceField;
import com.pnfsoftware.jeb.core.units.code.java.IJavaLeftExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaMethod;
import com.pnfsoftware.jeb.core.units.code.java.IJavaNewArray;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStaticField;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.core.units.code.java.JOptimizerType;
import com.pnfsoftware.jeb.core.units.code.java.JavaReconEnummap;
import java.util.HashSet;

public class bng extends AbstractJOptimizer {
   public bng() {
      super(JOptimizerType.UNSAFE);
   }

   @Override
   public int perform() {
      if (this.c == null) {
         return 0;
      } else {
         IJavaMethod var1 = null;

         for (IJavaMethod var3 : this.c.getMethods()) {
            if (var3.getName().equals("<clinit>")) {
               var1 = var3;
               break;
            }
         }

         if (var1 == null) {
            return -1;
         } else {
            IJavaBlock var14 = var1.getBody();
            if (var14.isEmpty()) {
               return 0;
            } else {
               IJavaLeftExpression var15 = null;
               IJavaType var4 = null;
               IJavaStatement var5 = var14.get(0);
               if (var5 instanceof IJavaAssignment) {
                  IJavaExpression var6 = ((IJavaAssignment)var5).getRight();
                  if (var6 instanceof IJavaNewArray) {
                     var15 = ((IJavaAssignment)var5).getLeft();
                     IJavaNewArray var7 = (IJavaNewArray)var6;
                     if (var7.getSizes() != null && var7.getSizes().size() == 1) {
                        IJavaExpression var8 = (IJavaExpression)var7.getSizes().get(0);
                        if (var8 instanceof IJavaInstanceField && ((IJavaInstanceField)var8).getFieldName().equals("length")) {
                           IJavaExpression var9 = ((IJavaInstanceField)var8).getInstance();
                           if (var9 instanceof IJavaCall && ((IJavaCall)var9).getMethodName().equals("values")) {
                              String var10 = ((IJavaCall)var9).getMethodClass();
                              var4 = this.tf.createType(var10);
                              IJavaClass var11 = this.jctx.getClassFactory().get(var10);
                              if (var11 == null || !var11.isBuilt()) {
                                 if (this.drcollector != null) {
                                    this.drcollector.request(var10);
                                 }

                                 return 0;
                              }
                           }
                        }
                     }
                  }
               }

               if (var4 == null) {
                  return 0;
               } else {
                  JavaReconEnummap var17 = new JavaReconEnummap(var4);
                  boa var18 = new boa(var1);
                  var18.kS(1);
                  HashSet var19 = new HashSet();
                  var19.add(0);
                  var19.add(1);

                  while (true) {
                     var5 = var18.E();
                     if (var5 == null || !var19.add(var18.UT())) {
                        this.c.setReconEnummap(var17);
                        return 1;
                     }

                     if (!(var5 instanceof boi) && var5 instanceof IJavaAssignment && ((IJavaAssignment)var5).isSimpleAssignment()) {
                        IJavaExpression var20 = ((IJavaAssignment)var5).getRight();
                        if (var20 instanceof IJavaConstant) {
                           int var21 = ((IJavaConstant)var20).getInt();
                           IJavaLeftExpression var22 = ((IJavaAssignment)var5).getLeft();
                           if (var22 instanceof IJavaArrayElt && ((IJavaArrayElt)var22).getArray().equals(var15)) {
                              IJavaExpression var12 = ((IJavaArrayElt)var22).getIndex();
                              if (var12 instanceof IJavaCall && ((IJavaCall)var12).getMethodName().equals("ordinal")) {
                                 IJavaExpression var13 = ((IJavaCall)var12).getArgument(0);
                                 if (var13 instanceof IJavaStaticField) {
                                    var17.getMap().put(var21, ((IJavaStaticField)var13).getFieldSignature());
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }
}
