package com.pnfsoftware.jeb.core.units;

import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.actions.ActionContext;
import com.pnfsoftware.jeb.core.actions.IActionData;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Collections;
import java.util.List;

@Ser
public abstract class AbstractInteractiveUnit extends AbstractUnit implements IInteractiveUnit {
   @SerId(1)
   private IMetadataManager metadataManager = new MetadataManager();

   public AbstractInteractiveUnit(String var1, String var2, IUnit var3) {
      this(var1, var2, var3.getUnitProcessor(), var3, var3.getPropertyDefinitionManager());
   }

   public AbstractInteractiveUnit(String var1, String var2, IUnitProcessor var3, IUnitCreator var4, IPropertyDefinitionManager var5) {
      super(var1, var2, var3, var4, var5);
   }

   @Override
   public Object getItemObject(long var1) {
      return null;
   }

   @Override
   public List getGlobalActions() {
      return Collections.emptyList();
   }

   @Override
   public List getItemActions(long var1) {
      return Collections.emptyList();
   }

   @Override
   public boolean isValidAddress(String var1) {
      return this.getItemAtAddress(var1) != 0L;
   }

   @Override
   public List getAddressActions(String var1) {
      return Collections.emptyList();
   }

   @Override
   public String getAddressOfItem(long var1) {
      return null;
   }

   @Override
   public List getRelatedItems(long var1) {
      return Collections.emptyList();
   }

   @Override
   public long getItemAtAddress(String var1) {
      return 0L;
   }

   @Override
   public boolean canExecuteAction(ActionContext var1) {
      return false;
   }

   @Override
   public boolean prepareExecution(ActionContext var1, IActionData var2) {
      return false;
   }

   @Override
   public boolean executeAction(ActionContext var1, IActionData var2) {
      return this.executeAction(var1, var2, true);
   }

   @Override
   public boolean executeAction(ActionContext var1, IActionData var2, boolean var3) {
      return false;
   }

   @Override
   public IMetadataManager getMetadataManager() {
      return this.metadataManager;
   }
}
