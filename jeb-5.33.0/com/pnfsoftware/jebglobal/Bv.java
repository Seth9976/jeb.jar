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

public abstract class Bv extends AbstractPlugin implements IUnitContribution {
   private IUnit pC;
   private IPluginInformation A;

   public Bv(IUnit var1, String var2) {
      this.pC = var1;
      this.A = new PluginInformation(var2, S.L("This is an internal contribution plugin for JEB"), "PNF Software", null);
   }

   @Override
   public boolean isTarget(IUnit var1) {
      return var1 == this.pC;
   }

   @Override
   public void setPrimaryTarget(IUnit var1) {
   }

   @Override
   public IUnit getPrimaryTarget() {
      return this.pC;
   }

   @Override
   public IPluginInformation getPluginInformation() {
      return this.A;
   }

   @Override
   public TypedContent getLocationInformation(IAddressableUnit var1, String var2) {
      return null;
   }

   protected TypedContent sY(String var1) {
      return TypedContent.html("<pre>" + Formatter.htmlEscape(var1) + "</pre>");
   }

   protected TypedContent pC(String var1, int var2) {
      return TypedContent.html("<pre style=\"font-size: " + var2 + "%;\">" + Formatter.htmlEscape(var1) + "</pre>");
   }
}
