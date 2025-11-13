package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.actions.ActionCommentData;
import com.pnfsoftware.jeb.core.actions.ActionContext;
import com.pnfsoftware.jeb.core.actions.ActionCreatePackageData;
import com.pnfsoftware.jeb.core.actions.ActionMoveToData;
import com.pnfsoftware.jeb.core.actions.ActionMoveToPackageData;
import com.pnfsoftware.jeb.core.actions.ActionOverridesData;
import com.pnfsoftware.jeb.core.actions.ActionRenameData;
import com.pnfsoftware.jeb.core.actions.ActionReplaceData;
import com.pnfsoftware.jeb.core.actions.ActionTypeHierarchyData;
import com.pnfsoftware.jeb.core.actions.ActionXrefsData;
import com.pnfsoftware.jeb.core.actions.IActionData;
import com.pnfsoftware.jeb.core.units.code.ICodeClass;
import com.pnfsoftware.jeb.core.units.code.ICodeField;
import com.pnfsoftware.jeb.core.units.code.ICodeMethod;
import com.pnfsoftware.jeb.core.units.code.ICodePackage;
import com.pnfsoftware.jeb.core.units.code.IInstructionOperand;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.IReference;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ReferenceLocation;
import com.pnfsoftware.jeb.core.units.code.asm.items.DataHints;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeDataItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMemoryItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.core.units.code.asm.processor.IInstructionOperandGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.processor.InstructionUtil;
import com.pnfsoftware.jeb.core.units.code.asm.render.AddressFormatter;
import com.pnfsoftware.jeb.core.units.code.asm.render.ConstantsFormatter;
import com.pnfsoftware.jeb.core.units.code.asm.render.NativeDisassemblyProperties;
import com.pnfsoftware.jeb.core.units.code.asm.render.NumberFormatter;
import com.pnfsoftware.jeb.core.units.code.asm.type.CodeConstant;
import com.pnfsoftware.jeb.core.units.code.asm.type.IClassType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStructureType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStructureTypeField;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.math.MathUtil;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInit;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Ser
public class YK {
   private static final ILogger A = GlobalLog.getLogger(YK.class);
   @SerId(1)
   C pC;

   @SerCustomInit
   private void A() {
   }

   YK(C var1) {
      this.pC = var1;
   }

   public List pC() {
      ArrayList var1 = new ArrayList();
      var1.add(10);
      return var1;
   }

   public List pC(long var1) {
      return Collections.emptyList();
   }

   public List pC(String var1) {
      ArrayList var2 = new ArrayList();
      var2.add(2);
      var2.add(3);
      return var2;
   }

   boolean pC(ActionContext var1) {
      INativeItem var2 = this.pC.getItemObject(var1.getItemId());
      long var3 = -1L;
      if (!(var2 instanceof ICodePackage)) {
         var3 = this.pC.getCanonicalMemoryAddress(var1.getAddress(), var1.getAddressPrecision());
      }

      switch (var1.getActionId()) {
         case 2:
            if (var2 != null && !(var2 instanceof INativeMemoryItem)) {
               return var2.getName(true) != null;
            }

            return var3 != -1L || var2 != null;
         case 3:
         case 4:
            return var3 != -1L || var2 != null && !(var2 instanceof ICodePackage);
         case 5:
         case 6:
            return var2 instanceof avh || var2 instanceof avd || var2 instanceof INativeDataItem;
         case 7:
         case 8:
         case 9:
         case 14:
         case 15:
         default:
            return false;
         case 10:
            return true;
         case 11:
         case 16:
            if (var3 == -1L && var2 == null) {
               return false;
            }

            return var2 instanceof ICodeClass || var2 instanceof ICodeField || var2 instanceof ICodeMethod || this.pC.A(var3) != null;
         case 12:
            return var2 instanceof ICodeClass || var2 instanceof IClassType;
         case 13:
            return var2 instanceof ICodeMethod && ((ICodeMethod)var2).getClassType() != null;
      }
   }

   boolean pC(ActionContext var1, IActionData var2, boolean var3, boolean var4) {
      if (var3) {
         return this.A(var1, var2, var3, var4);
      } else {
         this.pC.pC(true);

         boolean var5;
         try {
            var5 = this.A(var1, var2, var3, var4);
         } finally {
            this.pC.pC(false);
         }

         return var5;
      }
   }

   INativeItem A(long var1) {
      auu var3 = this.pC.A(var1);
      return (INativeItem)(var3 != null ? var3 : this.pC.getNativeItemAt(var1));
   }

   private boolean A(ActionContext var1, IActionData var2, boolean var3, boolean var4) {
      INativeItem var5 = this.pC.getItemObject(var1.getItemId());
      long var6 = this.pC.getCanonicalMemoryAddress(var1.getAddress());
      int var8 = var1.getActionId();
      switch (var8) {
         case 2:
            ActionRenameData var45 = (ActionRenameData)var2;
            if (var5 != null) {
               if (!(var5 instanceof INativeMemoryItem)) {
                  if (var3) {
                     var45.setCurrentName(var5.getName(true));
                  } else {
                     var5.setName(var45.getNewName());
                  }
                  break;
               }

               var6 = ((INativeMemoryItem)var5).getMemoryAddress();
            }

            if (var6 == 0L) {
               return false;
            }

            var5 = this.A(var6);
            if (var3) {
               var45.setCurrentName(var5 != null ? var5.getName(true) : this.pC.wS(var6));
            } else if (var5 != null) {
               var5.setName(var45.getNewName());
            } else {
               this.pC.pC(var6, var45.getNewName());
            }
            break;
         case 3:
            ActionCommentData var44 = (ActionCommentData)var2;
            if (var6 == 0L) {
               return false;
            }

            if (var3) {
               var44.setComment(this.pC.getCodeAnalyzer().getModel().getCommentManager().getComment(var6));
            } else {
               this.pC.getCodeAnalyzer().getModel().getCommentManager().setComment(var6, var44.getNewComment());
            }
            break;
         case 4:
            Long var43 = var6 < 0L ? null : var6;
            if (var5 != null) {
               Long var54 = this.pC.pC(var5);
               if (var54 != null) {
                  var43 = var54;
               }
            }

            ReferenceLocation var55;
            String var63;
            if (var43 != null && var43 != 0L) {
               var55 = ReferenceLocation.create(var43);
               var63 = this.pC.wS(var43);
            } else {
               if (!(var5 instanceof auu)) {
                  return false;
               }

               var55 = ReferenceLocation.createFromExternalRoutine((INativeMethodItem)var5);
               var63 = var5.getAddress();
            }

            if (!var3) {
               return true;
            }

            ActionXrefsData var67 = (ActionXrefsData)var2;
            var67.setTarget(var63);
            ArrayList var69 = new ArrayList();
            var67.setAddresses(var69);
            ArrayList var70 = new ArrayList();
            var67.setDetails(var70);
            ArrayList var72 = new ArrayList();
            var67.setUserAddresses(var72);
            Set var74 = this.pC.getCodeModel().getReferenceManager().getReferencesTo(var55);
            TreeSet var76 = new TreeSet();
            if (!var74.isEmpty()) {
               var76.addAll(var74);
            }

            if (var43 != null && var43 != 0L && this.pC.getCodeModel().getItemAt(var43) instanceof INativeDataItem var19) {
               DataHints var20 = var19.getHints(false);
               if (var20 != null && var20.getReferenceHint() != 0) {
                  for (long var21 = var19.getMemoryAddress() + 1L; var21 < var19.getMemoryAddressEnd(); var21++) {
                     for (IReference var25 : this.pC.getCodeModel().getReferenceManager().getReferencesTo(var21)) {
                        IReference var26 = (IReference)var76.floor(var25);
                        if (var26 == null || !var26.getFrom().equals(var25.getFrom())) {
                           var76.add(var25);
                        }
                     }
                  }
               }
            }

            axz var78 = new axz(this.pC);
            String var79 = this.pC.getCodeFormatter().getInlineCommentString();

            try {
               NativeDisassemblyProperties var80 = new NativeDisassemblyProperties();
               var80.setShowAddresses(false);
               var80.setShowBytesCount(0);
               var80.setLabelAreaLength(0);
               var78.setPropertyOverrides(var80);

               for (IReference var22 : var76) {
                  if (var22.getFrom().isInternalAddress()) {
                     long var82 = var22.getFrom().getInternalAddress();
                     auu var84 = this.pC.pC(var82, false);
                     String var83;
                     if (var84 != null) {
                        long var27 = var84.E().getMemoryAddress();
                        var83 = this.pC.wS(var27);
                        long var29 = var82 - var27;
                        if (var29 > 0L) {
                           var83 = Strings.ff("%s+%Xh", var83, var29);
                        } else if (var29 < 0L) {
                           var83 = Strings.ff("%s-%Xh", var83, -var29);
                        }
                     } else {
                        var83 = this.pC.wS(var82);
                     }

                     if (var83 == null) {
                        var83 = Strings.ff("%Xh", var82);
                     }

                     var69.add(var83);
                     var72.add(Strings.ff("%Xh", var82));
                     String var85 = null;
                     INativeContinuousItem var28 = this.pC.getNativeItemAt(var82);
                     if (var28 != null) {
                        String var89 = var78.pC(var28);
                        String[] var91 = Strings.splitLines(var89);

                        for (int var92 = var91.length - 1; var92 >= 0; var92--) {
                           if (!Strings.isBlank(var91[var92]) && !var91[var92].trim().startsWith(var79)) {
                              var85 = var91[var92];
                              break;
                           }
                        }
                     } else {
                        var28 = this.pC.getCodeModel().getItemOver(var82);
                        if (var28 instanceof auw var87) {
                           if (var87.UT() instanceof IStructureType) {
                              IStructureType var30 = (IStructureType)var87.UT();
                              IStructureTypeField var31 = var30.getFieldOver((int)(var82 - var28.getMemoryAddress()));
                              var85 = var31.getName();
                           }
                        } else if (var28 instanceof auv) {
                           aye var88 = ((auv)var28).z();
                           int var90 = (int)(var82 - var28.getMemoryAddress());
                           if (var90 % var88.getSize() == 0) {
                              var85 = "[" + var90 / var88.getSize() + "]";
                           }
                        }
                     }

                     var70.add(var85);
                  }
               }
               break;
            } finally {
               var78.dispose();
            }
         case 5:
         case 6:
            if (var5 instanceof avd) {
               if (var8 == 6) {
                  return false;
               }

               if (var3) {
                  return true;
               }

               AddressFormatter var51 = this.pC.getCodeFormatter().getDefaultAddressFormatter();
               var51.setBase(var51.getBase().nextBase());
            } else if (var5 instanceof avh var52) {
               int var62 = var52.wS();
               if (!(this.pC.getNativeItemAt(var52.kS()) instanceof aus var68)) {
                  return false;
               }

               if (var68.getInstruction() == null) {
                  return false;
               }

               IInstructionOperand var14 = InstructionUtil.getOperandByGlobalIndex(var68.getInstruction(), var62);
               if (var14 == null) {
                  return false;
               }

               if (var3) {
                  if (var8 == 6) {
                     ActionReplaceData var71 = (ActionReplaceData)var2;
                     if (!(var14 instanceof IInstructionOperandGeneric)) {
                        return false;
                     }

                     int var73 = ((IInstructionOperandGeneric)var14).getOperandBitsize();
                     long var75 = ((IInstructionOperandGeneric)var14).getOperandValue();
                     if (var73 <= 32) {
                        var71.setTargetObject((int)MathUtil.zeroExtend(var75, var73));
                     } else {
                        if (var73 > 64) {
                           return false;
                        }

                        var71.setTargetObject(MathUtil.zeroExtend(var75, var73));
                     }
                  }

                  return true;
               }

               NumberFormatter var15 = this.pC.getCodeFormatter().getNumberFormatter(var14, true);
               if (var8 == 5) {
                  var15.setBase(var15.getBase().nextBase());
               } else {
                  ActionReplaceData var16 = (ActionReplaceData)var2;
                  if (!(var16.getWantedReplacement() instanceof CodeConstant)) {
                     return false;
                  }

                  CodeConstant var17 = (CodeConstant)var16.getWantedReplacement();
                  ConstantsFormatter var18 = new ConstantsFormatter(var17);
                  var15.setConstantsFormatterOverride(var18);
               }
            } else if (var5 instanceof aum var42) {
               if (var3) {
                  return true;
               }

               NumberFormatter var53 = var42.ys();
               var53.setEndianness(this.pC.getEndianness());
               if (var8 != 5) {
                  return false;
               }

               var53.setBase(var53.getBase().nextBase());
            }
            break;
         case 7:
         case 8:
         case 9:
         case 14:
         case 15:
         default:
            return false;
         case 10:
            ActionCreatePackageData var41 = (ActionCreatePackageData)var2;
            if (var3) {
               if (var5 instanceof ICodePackage) {
                  var41.setCurrentPackageFqname(var5.getAddress());
               } else if (var5 instanceof INativeMethodItem) {
                  String var49 = var5.getAddress();
                  int var60 = var49.lastIndexOf("::");
                  if (var60 > 0) {
                     var41.setCurrentPackageFqname(var49.substring(0, var60));
                  }
               }

               var41.setDescription("A native package name uses the '::' separator for its components.\nExample: 'util::networks' or 'base'");
            } else {
               String var50 = var41.getFqname();
               ayo var61 = this.pC.A().sY(var50);
               if (var61 == null) {
                  return false;
               }
            }
            break;
         case 11:
            ActionMoveToPackageData var40 = (ActionMoveToPackageData)var2;
            if (var5 == null) {
               var5 = this.pC.A(var6);
            }

            if (var5 instanceof avd) {
               var6 = ((avd)var5).getMemoryAddress();
               var5 = this.pC.A(var6);
            }

            if (!(var5 instanceof auu) && !(var5 instanceof aur) && !(var5 instanceof auq)) {
               return false;
            }

            if (var3) {
               var40.setCurrentPackageFqname("");
               var40.setDescription(
                  Strings.ff(
                     "The item '%s' will be moved to the provided package name.\nA native package name uses the '::' separator for its components.",
                     var5.getName(true)
                  )
               );
            } else {
               String var48 = var40.getDstPackageFqname();
               if (var48 == null) {
                  return false;
               }

               ayy var59 = this.pC.A();
               ayp var65 = var59.ys(var48);
               if (var65 == null || !var59.moveToPackage(var5, var65)) {
                  return false;
               }
            }
            break;
         case 12:
            if (!var3) {
               return true;
            }

            ActionTypeHierarchyData var39 = (ActionTypeHierarchyData)var2;
            if (var5 instanceof ayi) {
               var5 = ((ayi)var5).E();
            }

            if (var5 instanceof auq var47) {
               ayo var57 = this.A(var47.sY());
               var39.setBaseNode(var57);
               var57 = this.pC(var47.sY());
               var39.setBaseNodeForAscendingHierarchy(var57);
            }
            break;
         case 13:
            if (!var3) {
               return true;
            }

            ActionOverridesData var38 = (ActionOverridesData)var2;
            if (var5 instanceof auu var46) {
               auq var56 = var46.ld();
               if (var56 != null) {
                  ArrayList var64 = new ArrayList();
                  ArrayList var13 = new ArrayList();
                  if (var56.collectVirtualMethodOverrides(var46, var64, var13)) {
                     var38.setItems(var13, var64);
                  }
               }
            }
            break;
         case 16:
            ActionMoveToData var9 = (ActionMoveToData)var2;
            if (var5 == null) {
               var5 = this.pC.A(var6);
            }

            if (var5 instanceof avd) {
               var6 = ((avd)var5).getMemoryAddress();
               var5 = this.pC.A(var6);
            }

            if (!(var5 instanceof auu) && !(var5 instanceof aur) && !(var5 instanceof auq)) {
               return false;
            }

            if (var3) {
               var9.setCurrentItemFqname("");
               var9.setDescription(
                  Strings.ff(
                     "The item '%s' will be moved to the provided package name.\nA native package name uses the '::' separator for its components.",
                     var5.getName(true)
                  )
               );
            } else {
               String var10 = var9.getDstContainerFqname();
               if (var10 == null) {
                  return false;
               }

               ayy var11 = this.pC.A();
               ayp var12 = var11.ys(var10);
               if (var12 == null || !var11.moveToPackage(var5, var12)) {
                  return false;
               }
            }
      }

      if (!var3 && var4) {
         this.pC.gp();
      }

      return true;
   }

   ayo pC(ayi var1) {
      if (var1.ld().isEmpty()) {
         return null;
      } else {
         ayo var2 = new ayo(var1);
         this.pC(var2, var1);
         return var2;
      }
   }

   private void pC(ayo var1, ayi var2) {
      for (ayi var4 : var2.ld()) {
         ayo var5 = new ayo(var4);
         var1.A(var5);
         this.pC(var5, var4);
      }
   }

   ayo A(ayi var1) {
      ayo var2 = new ayo(var1);
      this.A(var2, var1);
      return var2;
   }

   private void A(ayo var1, ayi var2) {
      for (ayi var4 : var2.UO()) {
         ayo var5 = new ayo(var4);
         var1.A(var5);
         this.A(var5, var4);
      }
   }
}
