package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.output.code.coordinates.MethodCoordinates;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IDynamicContentManager;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICCall;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICConstantInteger;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICConstantInteger32;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICConstantInteger64;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICMethod;
import com.pnfsoftware.jeb.core.units.code.asm.render.ConstantsFormatter;
import com.pnfsoftware.jeb.core.units.code.asm.type.CodeConstant;
import com.pnfsoftware.jeb.core.units.code.asm.type.ITypeManager;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.Map.Entry;

public class afd {
   private static final ILogger q = GlobalLog.getLogger(aez.class);
   private ITypeManager RF;
   private IDynamicContentManager xK;

   public afd(ITypeManager var1, IDynamicContentManager var2) {
      this.RF = var1;
      this.xK = var2;
   }

   public int q(ICElement var1) {
      return this.RF(var1);
   }

   private int RF(ICElement var1) {
      return this.q(var1, null);
   }

   private int q(ICElement var1, ICElement var2) {
      int var3 = this.RF(var1, var2);

      for (ICElement var5 : var1.getSubElements()) {
         if (!(var5 instanceof ICMethod)) {
            var3 += this.q(var5, var1);
         }
      }

      return var3;
   }

   private int RF(ICElement var1, ICElement var2) {
      return var1 instanceof ICCall ? this.q((ICCall)var1) : 0;
   }

   private int q(ICCall var1) {
      ICMethod var2 = var1.getMethod();
      if (var2 != null && var1.getArgumentCount() != 0) {
         String var3 = this.xK == null ? null : this.xK.getMethodName(new MethodCoordinates(var2.getIndex()));
         if (var3 == null) {
            var3 = var2.getName();
         }

         com.pnfsoftware.jeb.corei.parsers.asm.type.axgen.CU var4 = com.pnfsoftware.jeb.corei.parsers.asm.type.axgen.EE.q().q(var3);
         if (var4 == null) {
            return 0;
         } else {
            int var5 = 0;
            List var6 = var1.getArguments();

            for (int var7 = 0; var7 < var6.size(); var7++) {
               ICExpression var8 = (ICExpression)var6.get(var7);
               Long var9 = null;
               if (var8 instanceof ICConstantInteger32) {
                  var9 = ((Integer)((ICConstantInteger32)var8).getValue()).longValue();
               } else if (var8 instanceof ICConstantInteger64) {
                  var9 = (Long)((ICConstantInteger64)var8).getValue();
               }

               if (var9 != null) {
                  com.pnfsoftware.jeb.corei.parsers.asm.type.axgen.iZ var10 = var4.q(var7 + 1);
                  if (var10 != null && var10.RF() != null) {
                     if (var10.RF().q() == 3) {
                        for (String var21 : var10.RF().RF()) {
                           Long var23 = this.RF.getTypeLibraryService().findFirstIntegerConstantByName(var21);
                           if (var9.equals(var23)) {
                              Object[] var10000 = new Object[]{var21};
                              ICConstantInteger var24 = ((ICConstantInteger)var8).duplicate2();
                              if (var1.replaceSubElement(var8, var24)) {
                                 CodeConstant var26 = new CodeConstant(var21, var23);
                                 ConstantsFormatter var28 = new ConstantsFormatter(var26);
                                 var24.getFormatter().setConstantsFormatterOverride(var28);
                                 var5++;
                              }
                              break;
                           }
                        }
                     } else if (var10.RF().q() == 4 && var9 != 0L) {
                        TreeMap var11 = new TreeMap();

                        for (String var13 : var10.RF().RF()) {
                           Long var14 = this.RF.getTypeLibraryService().findFirstIntegerConstantByName(var13);
                           if (var14 != null) {
                              var11.put(var14, var13);
                           }
                        }

                        ArrayList var20 = new ArrayList();
                        long var22 = var9;

                        for (Entry var16 : var11.entrySet()) {
                           long var17 = (Long)var16.getKey();
                           if (var17 != 0L && (var22 & var17) == var17) {
                              var20.add(new CodeConstant((String)var16.getValue(), var17));
                              var22 &= ~var17;
                              if (var22 == 0L) {
                                 break;
                              }
                           }
                        }

                        if (!var20.isEmpty()) {
                           ICConstantInteger var25 = ((ICConstantInteger)var8).duplicate2();
                           if (var1.replaceSubElement(var8, var25)) {
                              ConstantsFormatter var27 = new ConstantsFormatter(var20, var22 == 0L ? null : var22);
                              var25.getFormatter().setConstantsFormatterOverride(var27);
                              var5++;
                           }
                        }
                     }
                  }
               }
            }

            return var5;
         }
      } else {
         return 0;
      }
   }
}
