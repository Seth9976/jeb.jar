package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.AbstractPlugin;
import com.pnfsoftware.jeb.core.IPluginInformation;
import com.pnfsoftware.jeb.core.IUnitContribution;
import com.pnfsoftware.jeb.core.PluginInformation;
import com.pnfsoftware.jeb.core.units.IAddressableUnit;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.util.base.TypedContent;
import com.pnfsoftware.jeb.util.format.Formatter;

public abstract class ct extends AbstractPlugin implements IUnitContribution {
   private IUnit q;
   private IPluginInformation RF;

   public ct(IUnit var1, String var2) {
      this.q = var1;
      this.RF = new PluginInformation(var2, S.L("This is an internal contribution plugin for JEB"), "PNF Software", null);
   }

   @Override
   public boolean isTarget(IUnit var1) {
      return var1 == this.q;
   }

   @Override
   public void setPrimaryTarget(IUnit var1) {
   }

   @Override
   public IUnit getPrimaryTarget() {
      return this.q;
   }

   @Override
   public IPluginInformation getPluginInformation() {
      return this.RF;
   }

   @Override
   public TypedContent getLocationInformation(IAddressableUnit var1, String var2) {
      return null;
   }

   protected TypedContent gO(String var1) {
      return TypedContent.html("<pre>" + Formatter.htmlEscape(var1) + "</pre>");
   }

   protected TypedContent q(String var1, int var2) {
      return TypedContent.html("<pre style=\"font-size: " + var2 + "%;\">" + Formatter.htmlEscape(var1) + "</pre>");
   }
}
