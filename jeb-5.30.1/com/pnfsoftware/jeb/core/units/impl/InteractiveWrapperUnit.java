package com.pnfsoftware.jeb.core.units.impl;

import com.pnfsoftware.jeb.core.actions.ActionContext;
import com.pnfsoftware.jeb.core.actions.IActionData;
import com.pnfsoftware.jeb.core.input.IInputLocation;
import com.pnfsoftware.jeb.core.units.IInteractiveUnit;
import com.pnfsoftware.jeb.core.units.IMetadataManager;
import com.pnfsoftware.jeb.core.units.IUnitProvider;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.List;
import java.util.Map;

@Ser
public class InteractiveWrapperUnit extends WrapperUnit implements IInteractiveUnit {
   public InteractiveWrapperUnit(IInteractiveUnit var1, IUnitProvider var2) {
      super(var1, var2);
   }

   @Override
   public IInputLocation addressToLocation(String var1) {
      return ((IInteractiveUnit)this.getWrap()).addressToLocation(var1);
   }

   @Override
   public boolean canExecuteAction(ActionContext var1) {
      return ((IInteractiveUnit)this.getWrap()).canExecuteAction(var1);
   }

   @Override
   public boolean executeAction(ActionContext var1, IActionData var2) {
      return ((IInteractiveUnit)this.getWrap()).executeAction(var1, var2);
   }

   @Override
   public boolean executeAction(ActionContext var1, IActionData var2, boolean var3) {
      return ((IInteractiveUnit)this.getWrap()).executeAction(var1, var2, var3);
   }

   @Override
   public List getAddressActions(String var1) {
      return ((IInteractiveUnit)this.getWrap()).getAddressActions(var1);
   }

   @Override
   public IMetadataManager getMetadataManager() {
      return ((IInteractiveUnit)this.getWrap()).getMetadataManager();
   }

   @Override
   public String getAddressLabel(String var1) {
      return ((IInteractiveUnit)this.getWrap()).getAddressLabel(var1);
   }

   @Override
   public Map getAddressLabels() {
      return ((IInteractiveUnit)this.getWrap()).getAddressLabels();
   }

   @Override
   public String getAddressOfItem(long var1) {
      return ((IInteractiveUnit)this.getWrap()).getAddressOfItem(var1);
   }

   @Override
   public List getRelatedItems(long var1) {
      return ((IInteractiveUnit)this.getWrap()).getRelatedItems(var1);
   }

   @Override
   public String getInlineComment(String var1) {
      return ((IInteractiveUnit)this.getWrap()).getInlineComment(var1);
   }

   @Override
   public Map getInlineComments() {
      return ((IInteractiveUnit)this.getWrap()).getInlineComments();
   }

   @Override
   public boolean setInlineComment(String var1, String var2) {
      return ((IInteractiveUnit)this.getWrap()).setInlineComment(var1, var2);
   }

   @Override
   public Object getItemObject(long var1) {
      return ((IInteractiveUnit)this.getWrap()).getItemObject(var1);
   }

   @Override
   public List getGlobalActions() {
      return ((IInteractiveUnit)this.getWrap()).getGlobalActions();
   }

   @Override
   public List getItemActions(long var1) {
      return ((IInteractiveUnit)this.getWrap()).getItemActions(var1);
   }

   @Override
   public boolean isValidAddress(String var1) {
      return ((IInteractiveUnit)this.getWrap()).isValidAddress(var1);
   }

   @Override
   public long getItemAtAddress(String var1) {
      return ((IInteractiveUnit)this.getWrap()).getItemAtAddress(var1);
   }

   @Override
   public String locationToAddress(IInputLocation var1) {
      return ((IInteractiveUnit)this.getWrap()).locationToAddress(var1);
   }

   @Override
   public boolean prepareExecution(ActionContext var1, IActionData var2) {
      return ((IInteractiveUnit)this.getWrap()).prepareExecution(var1, var2);
   }
}
