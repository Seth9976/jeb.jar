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

@Ser
public class abc {
   private static final ILogger RF = GlobalLog.getLogger(abc.class);
   @SerId(1)
   abg q;

   @SerCustomInit
   private void xK() {
   }

   abc(abg var1) {
      this.q = var1;
   }

   void q() {
   }

   public List RF() {
      ArrayList var1 = new ArrayList();
      var1.add(10);
      return var1;
   }

   public List q(long var1) {
      return Collections.emptyList();
   }

   public List q(String var1) {
      ArrayList var2 = new ArrayList();
      var2.add(2);
      var2.add(3);
      return var2;
   }

   boolean q(ActionContext var1) {
      INativeItem var2 = this.q.getItemObject(var1.getItemId());
      long var3 = -1L;
      if (!(var2 instanceof ICodePackage)) {
         var3 = this.q.getCanonicalMemoryAddress(var1.getAddress(), var1.getAddressPrecision());
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
            return var2 instanceof ayd || var2 instanceof axz || var2 instanceof INativeDataItem;
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

            return var2 instanceof ICodeClass || var2 instanceof ICodeField || var2 instanceof ICodeMethod || this.q.RF(var3) != null;
         case 12:
            return var2 instanceof ICodeClass || var2 instanceof IClassType;
         case 13:
            return var2 instanceof ICodeMethod && ((ICodeMethod)var2).getClassType() != null;
      }
   }

   boolean q(ActionContext var1, IActionData var2, boolean var3, boolean var4) {
      if (var3) {
         return this.RF(var1, var2, var3, var4);
      } else {
         this.q.q(true);

         boolean var5;
         try {
            var5 = this.RF(var1, var2, var3, var4);
         } finally {
            this.q.q(false);
         }

         return var5;
      }
   }

   INativeItem RF(long var1) {
      axp var3 = this.q.RF(var1);
      return (INativeItem)(var3 != null ? var3 : this.q.getNativeItemAt(var1));
   }

   private boolean RF(ActionContext var1, IActionData var2, boolean var3, boolean var4) {
      INativeItem var5 = this.q.getItemObject(var1.getItemId());
      long var6 = this.q.getCanonicalMemoryAddress(var1.getAddress());
      int var8 = var1.getActionId();
      switch (var8) {
         case 2:
            ActionRenameData var43 = (ActionRenameData)var2;
            if (var5 != null) {
               if (!(var5 instanceof INativeMemoryItem)) {
                  if (var3) {
                     var43.setCurrentName(var5.getName(true));
                  } else {
                     var5.setName(var43.getNewName());
                  }
                  break;
               }

               var6 = ((INativeMemoryItem)var5).getMemoryAddress();
            }

            if (var6 == 0L) {
               return false;
            }

            var5 = this.RF(var6);
            if (var3) {
               var43.setCurrentName(var5 != null ? var5.getName(true) : this.q.oW(var6));
            } else if (var5 != null) {
               var5.setName(var43.getNewName());
            } else {
               this.q.q(var6, var43.getNewName());
            }
            break;
         case 3:
            ActionCommentData var42 = (ActionCommentData)var2;
            if (var6 == 0L) {
               return false;
            }

            if (var3) {
               var42.setComment(this.q.getCodeAnalyzer().getModel().getCommentManager().getComment(var6));
            } else {
               this.q.getCodeAnalyzer().getModel().getCommentManager().setComment(var6, var42.getNewComment());
            }
            break;
         case 4:
            Long var41 = var6 < 0L ? null : var6;
            if (var5 != null) {
               Long var52 = this.q.q(var5);
               if (var52 != null) {
                  var41 = var52;
               }
            }

            ReferenceLocation var53;
            String var61;
            if (var41 != null && var41 != 0L) {
               var53 = ReferenceLocation.create(var41);
               var61 = this.q.oW(var41);
            } else {
               if (!(var5 instanceof axp)) {
                  return false;
               }

               var53 = ReferenceLocation.createFromExternalRoutine((INativeMethodItem)var5);
               var61 = var5.getAddress();
            }

            if (!var3) {
               return true;
            }

            ActionXrefsData var65 = (ActionXrefsData)var2;
            var65.setTarget(var61);
            ArrayList var67 = new ArrayList();
            var65.setAddresses(var67);
            ArrayList var68 = new ArrayList();
            var65.setDetails(var68);
            Set var70 = this.q.getCodeModel().getReferenceManager().getReferencesTo(var53);
            if (!var70.isEmpty()) {
               bay var72 = new bay(this.q);
               String var74 = this.q.getCodeFormatter().getInlineCommentString();

               try {
                  NativeDisassemblyProperties var75 = new NativeDisassemblyProperties();
                  var75.setShowAddresses(false);
                  var75.setShowBytesCount(0);
                  var75.setLabelAreaLength(0);
                  var72.setPropertyOverrides(var75);

                  for (IReference var20 : var70) {
                     if (var20.getFrom().isInternalAddress()) {
                        long var21 = var20.getFrom().getInternalAddress();
                        axp var24 = this.q.q(var21, false);
                        String var23;
                        if (var24 != null) {
                           long var25 = var24.oW().getMemoryAddress();
                           var23 = this.q.oW(var25);
                           long var27 = var21 - var25;
                           if (var27 > 0L) {
                              var23 = Strings.ff("%s+%Xh", var23, var27);
                           } else if (var27 < 0L) {
                              var23 = Strings.ff("%s-%Xh", var23, -var27);
                           }
                        } else {
                           var23 = this.q.oW(var21);
                        }

                        if (var23 == null) {
                           var23 = Strings.ff("%Xh", var21);
                        }

                        var67.add(var23);
                        String var76 = null;
                        INativeContinuousItem var26 = this.q.getNativeItemAt(var21);
                        if (var26 != null) {
                           String var80 = var72.q(var26);
                           String[] var82 = Strings.splitLines(var80);

                           for (int var83 = var82.length - 1; var83 >= 0; var83--) {
                              if (!Strings.isBlank(var82[var83]) && !var82[var83].trim().startsWith(var74)) {
                                 var76 = var82[var83];
                                 break;
                              }
                           }
                        } else {
                           var26 = this.q.getCodeModel().getItemOver(var21);
                           if (var26 instanceof axr var78) {
                              if (var78.Uv() instanceof IStructureType) {
                                 IStructureType var28 = (IStructureType)var78.Uv();
                                 IStructureTypeField var29 = var28.getFieldOver((int)(var21 - var26.getMemoryAddress()));
                                 var76 = var29.getName();
                              }
                           } else if (var26 instanceof axq) {
                              bbd var79 = ((axq)var26).wF();
                              int var81 = (int)(var21 - var26.getMemoryAddress());
                              if (var81 % var79.getSize() == 0) {
                                 var76 = "[" + var81 / var79.getSize() + "]";
                              }
                           }
                        }

                        var68.add(var76);
                     }
                  }
               } finally {
                  var72.dispose();
               }
            }
            break;
         case 5:
         case 6:
            if (var5 instanceof axz) {
               if (var8 == 6) {
                  return false;
               }

               if (var3) {
                  return true;
               }

               AddressFormatter var49 = this.q.getCodeFormatter().getDefaultAddressFormatter();
               var49.setBase(var49.getBase().nextBase());
            } else if (var5 instanceof ayd var50) {
               int var60 = var50.Dw();
               if (!(this.q.getNativeItemAt(var50.xK()) instanceof axn var66)) {
                  return false;
               }

               if (var66.getInstruction() == null) {
                  return false;
               }

               IInstructionOperand var14 = InstructionUtil.getOperandByGlobalIndex(var66.getInstruction(), var60);
               if (var14 == null) {
                  return false;
               }

               if (var3) {
                  if (var8 == 6) {
                     ActionReplaceData var69 = (ActionReplaceData)var2;
                     if (!(var14 instanceof IInstructionOperandGeneric)) {
                        return false;
                     }

                     int var71 = ((IInstructionOperandGeneric)var14).getOperandBitsize();
                     long var73 = ((IInstructionOperandGeneric)var14).getOperandValue();
                     if (var71 <= 32) {
                        var69.setTargetObject((int)MathUtil.zeroExtend(var73, var71));
                     } else {
                        if (var71 > 64) {
                           return false;
                        }

                        var69.setTargetObject(MathUtil.zeroExtend(var73, var71));
                     }
                  }

                  return true;
               }

               NumberFormatter var15 = this.q.getCodeFormatter().getNumberFormatter(var14, true);
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
            } else if (var5 instanceof axh var40) {
               if (var3) {
                  return true;
               }

               NumberFormatter var51 = var40.nf();
               var51.setEndianness(this.q.getEndianness());
               if (var8 != 5) {
                  return false;
               }

               var51.setBase(var51.getBase().nextBase());
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
            ActionCreatePackageData var39 = (ActionCreatePackageData)var2;
            if (var3) {
               if (var5 instanceof ICodePackage) {
                  var39.setCurrentPackageFqname(var5.getAddress());
               } else if (var5 instanceof INativeMethodItem) {
                  String var47 = var5.getAddress();
                  int var58 = var47.lastIndexOf("::");
                  if (var58 > 0) {
                     var39.setCurrentPackageFqname(var47.substring(0, var58));
                  }
               }

               var39.setDescription("A native package name uses the '::' separator for its components.\nExample: 'util::networks' or 'base'");
            } else {
               String var48 = var39.getFqname();
               bbo var59 = this.q.RF().gO(var48);
               if (var59 == null) {
                  return false;
               }
            }
            break;
         case 11:
            ActionMoveToPackageData var38 = (ActionMoveToPackageData)var2;
            if (var5 == null) {
               var5 = this.q.RF(var6);
            }

            if (var5 instanceof axz) {
               var6 = ((axz)var5).getMemoryAddress();
               var5 = this.q.RF(var6);
            }

            if (!(var5 instanceof axp) && !(var5 instanceof axm) && !(var5 instanceof axl)) {
               return false;
            }

            if (var3) {
               var38.setCurrentPackageFqname("");
               var38.setDescription(
                  Strings.ff(
                     "The item '%s' will be moved to the provided package name.\nA native package name uses the '::' separator for its components.",
                     var5.getName(true)
                  )
               );
            } else {
               String var46 = var38.getDstPackageFqname();
               if (var46 == null) {
                  return false;
               }

               bby var57 = this.q.RF();
               bbp var63 = var57.gP(var46);
               if (var63 == null || !var57.moveToPackage(var5, var63)) {
                  return false;
               }
            }
            break;
         case 12:
            if (!var3) {
               return true;
            }

            ActionTypeHierarchyData var37 = (ActionTypeHierarchyData)var2;
            if (var5 instanceof bbi) {
               var5 = ((bbi)var5).oW();
            }

            if (var5 instanceof axl var45) {
               bbo var55 = this.RF(var45.gO());
               var37.setBaseNode(var55);
               var55 = this.q(var45.gO());
               var37.setBaseNodeForAscendingHierarchy(var55);
            }
            break;
         case 13:
            if (!var3) {
               return true;
            }

            ActionOverridesData var36 = (ActionOverridesData)var2;
            if (var5 instanceof axp var44) {
               axl var54 = var44.gP();
               if (var54 != null) {
                  ArrayList var62 = new ArrayList();
                  ArrayList var13 = new ArrayList();
                  if (var54.collectVirtualMethodOverrides(var44, var62, var13)) {
                     var36.setItems(var13, var62);
                  }
               }
            }
            break;
         case 16:
            ActionMoveToData var9 = (ActionMoveToData)var2;
            if (var5 == null) {
               var5 = this.q.RF(var6);
            }

            if (var5 instanceof axz) {
               var6 = ((axz)var5).getMemoryAddress();
               var5 = this.q.RF(var6);
            }

            if (!(var5 instanceof axp) && !(var5 instanceof axm) && !(var5 instanceof axl)) {
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

               bby var11 = this.q.RF();
               bbp var12 = var11.gP(var10);
               if (var12 == null || !var11.moveToPackage(var5, var12)) {
                  return false;
               }
            }
      }

      if (!var3 && var4) {
         this.q.lm();
      }

      return true;
   }

   bbo q(bbi var1) {
      if (var1.cC().isEmpty()) {
         return null;
      } else {
         bbo var2 = new bbo(var1);
         this.q(var2, var1);
         return var2;
      }
   }

   private void q(bbo var1, bbi var2) {
      for (bbi var4 : var2.cC()) {
         bbo var5 = new bbo(var4);
         var1.RF(var5);
         this.q(var5, var4);
      }
   }

   bbo RF(bbi var1) {
      bbo var2 = new bbo(var1);
      this.RF(var2, var1);
      return var2;
   }

   private void RF(bbo var1, bbi var2) {
      for (bbi var4 : var2.CE()) {
         bbo var5 = new bbo(var4);
         var1.RF(var5);
         this.RF(var5, var4);
      }
   }
}
