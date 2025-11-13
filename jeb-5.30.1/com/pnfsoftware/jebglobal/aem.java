package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IUnitContribution;
import com.pnfsoftware.jeb.core.actions.ActionContext;
import com.pnfsoftware.jeb.core.actions.ActionRenameData;
import com.pnfsoftware.jeb.core.actions.ActionReplaceData;
import com.pnfsoftware.jeb.core.actions.ActionSetTypeData;
import com.pnfsoftware.jeb.core.actions.IActionData;
import com.pnfsoftware.jeb.core.events.J;
import com.pnfsoftware.jeb.core.events.JebEvent;
import com.pnfsoftware.jeb.core.input.IInputLocation;
import com.pnfsoftware.jeb.core.output.IUnitFormatter;
import com.pnfsoftware.jeb.core.output.UnitFormatterUtil;
import com.pnfsoftware.jeb.core.output.code.coordinates.ClassCoordinates;
import com.pnfsoftware.jeb.core.output.code.coordinates.CodeCoordinatesUtil;
import com.pnfsoftware.jeb.core.output.code.coordinates.FieldCoordinates;
import com.pnfsoftware.jeb.core.output.code.coordinates.ICodeCoordinates;
import com.pnfsoftware.jeb.core.output.code.coordinates.InstructionCoordinates;
import com.pnfsoftware.jeb.core.output.code.coordinates.MethodCoordinates;
import com.pnfsoftware.jeb.core.output.text.ITextDocument;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.units.AbstractUnit;
import com.pnfsoftware.jeb.core.units.IMetadataManager;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IUnitLock;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.core.units.code.NativeCommentManager;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IDecompiledItem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IDecompiledMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeSourceUnit;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CDocument;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CIdentifierClass;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICClass;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICConstantInteger;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICField;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIdentifier;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICMethod;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeDataItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeItem;
import com.pnfsoftware.jeb.core.units.code.asm.render.ConstantsFormatter;
import com.pnfsoftware.jeb.core.units.code.asm.type.CodeConstant;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeStringParseException;
import com.pnfsoftware.jeb.util.events.IEventListener;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Ser
public class aem extends AbstractUnit implements INativeSourceUnit {
   private static final StructuredLogger RF = aeg.q(aem.class);
   @SerId(1)
   private IDecompiledItem xK;
   @SerId(2)
   private adw Dw;
   @SerId(3)
   private abg Uv;
   @SerId(4)
   private String oW;
   @SerId(5)
   private String gO;
   @SerId(6)
   private boolean nf;
   @SerTransient
   private IEventListener gP;
   public static boolean q = true;
   @SerTransient
   private IUnitContribution za;

   public aem(IDecompiledItem var1, String var2, String var3, String var4, String var5, IUnitProcessor var6, adw var7, IPropertyDefinitionManager var8) {
      super(var2, var5, var6, var7, var8);
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.xK = var1;
         this.Dw = var7;
         this.Uv = (abg)var7.getParent();
         this.gO = var3;
         this.oW = var4;
         this.Uv();
      }
   }

   @SerCustomInitPostGraph
   private void Uv() {
      this.oW();
   }

   private void oW() {
      if (this.gP == null) {
         this.Uv.addListener(this.gP = new aen(this));
      }
   }

   @Override
   public void setName(String var1) {
      RF.warn("Decompiled source units cannot be renamed; rename the code item itself instead (e.g. class, method, etc.)");
   }

   @Override
   public void dispose() {
      if (this.gP != null) {
         this.Uv.removeListener(this.gP);
         this.gP = null;
      }

      super.dispose();
   }

   @Override
   public INativeItem getNativeItem() {
      return this.xK == null ? null : this.xK.getNativeItem();
   }

   @Override
   public ICElement getASTItem() {
      return this.xK == null ? null : this.xK.getASTItem();
   }

   public void q(IDecompiledItem var1) {
      this.xK = var1;
   }

   @Override
   public IDecompiledItem getDecompiledItem() {
      return this.xK;
   }

   public adw q() {
      return this.Dw;
   }

   @Override
   public String getFileExtension() {
      return "c";
   }

   public String RF() {
      return this.gO;
   }

   @Override
   public String getFullyQualifiedName() {
      return this.oW;
   }

   @Override
   public IUnitLock getLock() {
      return this.Dw.getLock();
   }

   @Override
   public boolean isStale() {
      return this.nf;
   }

   public void xK() {
      this.setStatus("Source has been reset. A re-decompilation is required.");
      if (this.isProcessed()) {
         this.nf = true;
      }
   }

   @Override
   public boolean process() {
      if (this.isProcessed() && !this.isStale()) {
         return true;
      } else {
         this.Dw.decompileToUnit(this.gO);
         this.nf = false;
         return this.isProcessed();
      }
   }

   @Override
   public IUnitFormatter getFormatter() {
      IUnitFormatter var1 = super.getFormatter();
      if (UnitFormatterUtil.getPresentationByIdentifier(var1, 1L) == null) {
         var1.addPresentation(new aeo(this, 1L, S.L("Source"), true), false);
      }

      if (this.xK instanceof IDecompiledMethod) {
         IERoutineContext var2 = ((IDecompiledMethod)this.xK).getIRContext();
         if (var2 != null && UnitFormatterUtil.getPresentationByIdentifier(var1, 10L) == null) {
            var1.addPresentation(new aep(this, 10L, "IR code", false, var2), false);
         }
      }

      return var1;
   }

   @Override
   public ITextDocument getSourceDocument() {
      return new CDocument(this);
   }

   @Override
   public String getSource() {
      ITextDocument var1 = this.getSourceDocument();

      String var2;
      try {
         var2 = var1.format();
      } finally {
         var1.dispose();
      }

      return var2;
   }

   @Override
   public Object getItemObject(long var1) {
      Object var3 = this.Dw.RF().retrieve(var1);
      if (var3 != null) {
         return var3;
      } else {
         if ((var1 & -8502796096475496448L) == -8502796096475496448L) {
            int var4 = (int)(var1 >> 32) & 16777215;
            int var5 = (int)var1;
            ICElement var6 = this.getASTItem();
            if (var6 instanceof ICMethod var7) {
               if (var7.getIndex() == var4) {
                  return var7.getIdentifierManager().getIdentifier(var5);
               }
            } else if (var6 instanceof ICClass var15) {
               for (ICMethod var9 : var15.getMethods()) {
                  if (var9.getIndex() == var4) {
                     return var9.getIdentifierManager().getIdentifier(var5);
                  }
               }
            }
         }

         INativeItem var12 = this.Uv.getItemObject(var1);
         if (var12 instanceof ayf) {
            int var13 = ((ayf)var12).xK();
            int var14 = ((ayf)var12).Dw();
            ICElement var16 = this.getASTItem();
            if (var16 instanceof ICMethod var17) {
               if (var17.getIndex() == var13) {
                  return var17.getIdentifierManager().getIdentifierAt(var14);
               }
            } else if (var16 instanceof ICClass var18) {
               for (ICMethod var10 : var18.getMethods()) {
                  if (var10.getIndex() == var13) {
                     return var10.getIdentifierManager().getIdentifierAt(var14);
                  }
               }
            }
         }

         return var12;
      }
   }

   @Override
   public List getGlobalActions() {
      return this.Uv.getGlobalActions();
   }

   @Override
   public List getItemActions(long var1) {
      return this.Uv.getItemActions(var1);
   }

   @Override
   public List getAddressActions(String var1) {
      return this.Uv.getAddressActions(var1);
   }

   @Override
   public String getAddressOfItem(long var1) {
      return this.Uv.getAddressOfItem(var1);
   }

   @Override
   public List getRelatedItems(long var1) {
      return this.Uv.getRelatedItems(var1);
   }

   @Override
   public boolean isValidAddress(String var1) {
      ICodeCoordinates var2 = this.Uv.getCodeCoordinatesFromAddress(var1);
      if (var2 == null) {
         return false;
      } else if (q) {
         return true;
      } else {
         ICElement var3 = this.getASTItem();
         if (var3 instanceof ICMethod) {
            return var2 instanceof MethodCoordinates && ((MethodCoordinates)var2).getMethodId() == ((ICMethod)var3).getIndex()
               ? true
               : var2 instanceof InstructionCoordinates && ((InstructionCoordinates)var2).getMethodId() == ((ICMethod)var3).getIndex();
         } else if (var3 instanceof ICField) {
            return var2 instanceof FieldCoordinates && ((FieldCoordinates)var2).getFieldId() == ((ICField)var3).getIndex();
         } else if (var3 instanceof ICClass) {
            int var4 = ((ICClass)var3).getIndex();
            if (var2 instanceof ClassCoordinates && ((ClassCoordinates)var2).getClassId() == var4) {
               return true;
            } else {
               if (var2 instanceof FieldCoordinates) {
                  int var5 = ((FieldCoordinates)var2).getFieldId();

                  for (ICField var7 : ((ICClass)var3).getFields()) {
                     if (var7.getIndex() == var5) {
                        return true;
                     }
                  }
               }

               if (var2 instanceof MethodCoordinates || var2 instanceof InstructionCoordinates) {
                  int var8 = CodeCoordinatesUtil.getBaseObjectIndex(var2);

                  for (ICMethod var10 : ((ICClass)var3).getMethods()) {
                     if (var10.getIndex() == var8) {
                        return true;
                     }
                  }
               }

               return false;
            }
         } else {
            return false;
         }
      }
   }

   @Override
   public long getItemAtAddress(String var1) {
      return this.Uv.getItemAtAddress(var1);
   }

   @Override
   public boolean canExecuteAction(ActionContext var1) {
      Object var2 = this.getItemObject(var1.getItemId());
      if (var2 instanceof agh) {
         switch (var1.getActionId()) {
            case 2:
            case 4:
            case 15:
               return true;
            default:
               return false;
         }
      } else if (var2 instanceof afo) {
         switch (var1.getActionId()) {
            case 5:
            case 6:
               return true;
            default:
               return false;
         }
      } else {
         return this.Uv.canExecuteAction(var1);
      }
   }

   @Override
   public boolean prepareExecution(ActionContext var1, IActionData var2) {
      Object var3 = this.getItemObject(var1.getItemId());
      if (var3 instanceof ICIdentifier var6) {
         switch (var1.getActionId()) {
            case 2:
               ActionRenameData var7 = (ActionRenameData)var2;
               var7.setCurrentName(var6.getName());
               var7.setOriginalName(var6.getOriginalName());
               return true;
            case 4:
               return false;
            case 15:
               if (var6.getIdentifierClass() != CIdentifierClass.LOCAL) {
                  Object[] var10000 = new Object[0];
                  return false;
               }

               ActionSetTypeData var5 = (ActionSetTypeData)var2;
               var5.setCurrentType(var6.getType().getSignature());
               return true;
            default:
               return false;
         }
      } else if (var3 instanceof ICConstantInteger) {
         switch (var1.getActionId()) {
            case 5:
               return true;
            case 6:
               ActionReplaceData var4 = (ActionReplaceData)var2;
               if (var3 instanceof afs) {
                  var4.setTargetObject(((afs)var3).getValue());
                  return true;
               } else {
                  if (var3 instanceof aft) {
                     var4.setTargetObject(((aft)var3).getValue());
                     return true;
                  }

                  return false;
               }
            default:
               return false;
         }
      } else {
         return this.Uv.prepareExecution(var1, var2);
      }
   }

   @Override
   public boolean executeAction(ActionContext var1, IActionData var2) {
      return this.executeAction(var1, var2, true);
   }

   @Override
   public boolean executeAction(ActionContext var1, IActionData var2, boolean var3) {
      Object var4 = this.getItemObject(var1.getItemId());
      if (var4 instanceof agh var15) {
         switch (var1.getActionId()) {
            case 2:
               ActionRenameData var17 = (ActionRenameData)var2;
               var15.setName(var17.getNewName(), this.Dw.q());
            case 4:
               break;
            case 15:
               ActionSetTypeData var16 = (ActionSetTypeData)var2;
               String var18 = var16.getNewType();
               Object[] var10000 = new Object[]{var15, var18};

               try {
                  INativeType var19;
                  try {
                     var19 = this.Uv.RF().getParser().parseType(var18);
                  } catch (TypeStringParseException var13) {
                     RF.error(S.L("Failed to parse type: %s"), var18);
                     return false;
                  }

                  if (var15.getIdentifierClass() != CIdentifierClass.LOCAL) {
                     return false;
                  }

                  int var21 = var15.getAddress().intValue();
                  axp var10 = this.Uv.Uv(var15.getMethodIndex());
                  abq var11 = var10.oW().Uv();
                  INativeContinuousItem var12 = var11.getModel().getItemAt(var21);
                  if (var12 instanceof INativeDataItem && ((INativeDataItem)var12).getType() == var19) {
                     return true;
                  }

                  var11.undefineItems(var21, var21 + var19.getSize());
                  var11.defineItem(var21, var19);
                  var3 = true;
                  break;
               } catch (Exception var14) {
                  return false;
               }
            default:
               return false;
         }

         if (var3) {
            this.notifyListeners(new JebEvent(J.UnitChange));
         }

         return true;
      } else if (var4 instanceof ICConstantInteger var5) {
         switch (var1.getActionId()) {
            case 5:
               var5.getFormatter().rotateBase();
               break;
            case 6:
               ActionReplaceData var6 = (ActionReplaceData)var2;
               Object var7 = var6.getWantedReplacement();
               if (var7 instanceof CodeConstant var9) {
                  var8 = new ConstantsFormatter(var9);
               } else if (var7 instanceof CodeConstant[] var20) {
                  var8 = new ConstantsFormatter(Arrays.asList(var20));
               } else if (!(var7 instanceof ConstantsFormatter var8)) {
                  return false;
               }

               var5.getFormatter().setConstantsFormatterOverride(var8);
               break;
            default:
               return false;
         }

         if (var3) {
            this.notifyListeners(new JebEvent(J.UnitChange));
         }

         return true;
      } else {
         return this.Uv.executeAction(var1, var2, var3);
      }
   }

   @Override
   public IMetadataManager getMetadataManager() {
      return this.Uv.getMetadataManager();
   }

   @Override
   public Map getInlineComments() {
      return this.Uv.getInlineComments();
   }

   @Override
   public String getInlineComment(String var1) {
      return this.Uv.getInlineComment(var1);
   }

   @Override
   public boolean setInlineComment(String var1, String var2) {
      return this.Uv.setInlineComment(var1, var2);
   }

   public NativeCommentManager Dw() {
      return this.Uv.getCommentManager();
   }

   @Override
   public Map getAddressLabels() {
      return new HashMap();
   }

   @Override
   public String getAddressLabel(String var1) {
      return this.Uv.getAddressLabel(var1);
   }

   @Override
   public IInputLocation addressToLocation(String var1) {
      return this.Uv.addressToLocation(var1);
   }

   @Override
   public String locationToAddress(IInputLocation var1) {
      return this.Uv.locationToAddress(var1);
   }

   @Override
   public List getContributions() {
      List var1 = super.getContributions();
      synchronized (this) {
         if (this.za == null) {
            this.za = new aev(this);
            var1.add(this.za);
            var1.addAll(((IUnit)this.getParent()).getContributions());
         }

         return var1;
      }
   }
}
