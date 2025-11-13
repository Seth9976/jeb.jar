package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.actions.ActionCollapseData;
import com.pnfsoftware.jeb.core.actions.ActionContext;
import com.pnfsoftware.jeb.core.actions.ActionXrefsData;
import com.pnfsoftware.jeb.core.actions.IActionData;
import com.pnfsoftware.jeb.core.events.J;
import com.pnfsoftware.jeb.core.events.JebEvent;
import com.pnfsoftware.jeb.core.input.IInputLocation;
import com.pnfsoftware.jeb.core.output.IUnitFormatter;
import com.pnfsoftware.jeb.core.output.UnitFormatterUtil;
import com.pnfsoftware.jeb.core.output.code.coordinates.ICodeCoordinates;
import com.pnfsoftware.jeb.core.output.text.ITextDocument;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.units.AbstractUnit;
import com.pnfsoftware.jeb.core.units.IMetadataManager;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.core.units.UnitChangeEventData;
import com.pnfsoftware.jeb.core.units.code.android.DexCommentManager;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexItem;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaSourceUnit;
import com.pnfsoftware.jeb.core.units.code.java.JavaDocument;
import com.pnfsoftware.jeb.util.events.IEventListener;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

@Ser
public class bhv extends AbstractUnit implements IJavaSourceUnit {
   private static final ILogger pC = GlobalLog.getLogger(bhv.class);
   @SerId(value = 1, deprecated = true)
   @Deprecated
   private bjl A;
   @SerId(2)
   private com.pnfsoftware.jeb.corei.parsers.dexdec.Ws kS;
   @SerId(3)
   private com.pnfsoftware.jeb.corei.parsers.dex.vi wS;
   @SerId(4)
   private Map UT;
   @SerId(5)
   private String E;
   @SerTransient
   private IEventListener sY;

   public bhv(String var1, IUnitProcessor var2, com.pnfsoftware.jeb.corei.parsers.dexdec.Ws var3, IPropertyDefinitionManager var4) {
      super("java", pC(var1), var2, var3, var4);
      this.kS = var3;
      this.wS = (com.pnfsoftware.jeb.corei.parsers.dex.vi)var3.getParent();
      this.UT = new HashMap();
      this.E = var1;
      this.E();
      this.wS();
   }

   private static String pC(String var0) {
      return var0;
   }

   @SerCustomInitPostGraph
   private void wS() {
      this.UT();
      this.A = null;
   }

   private void UT() {
      if (this.sY == null) {
         this.wS.addListener(this.sY = new bhw(this));
      }
   }

   private boolean E() {
      if (this.E != null) {
         Object var1 = this.A() ? this.wS.wS(this.E) : this.wS.gp(this.E);
         if (var1 != null) {
            String var2 = this.getName();
            String var3 = ((IDexItem)var1).getName(true);
            if (var2 == null || !var2.equals(var3)) {
               this.setName(var3);
               return true;
            }
         }
      }

      return false;
   }

   public com.pnfsoftware.jeb.corei.parsers.dexdec.Ws pC() {
      return this.kS;
   }

   boolean A() {
      return this.E.contains("(");
   }

   @Override
   public IJavaElement getASTElement() {
      return (IJavaElement)(this.A() ? this.kS.UT(this.E) : this.kS.kS(this.E));
   }

   @Override
   public IDexItem getDexItem() {
      return (IDexItem)(this.A() ? this.wS.wS(this.E) : this.wS.gp(this.E));
   }

   @Override
   public String getFileExtension() {
      return "java";
   }

   @Override
   public boolean process() {
      if (this.isProcessed()) {
         return true;
      } else {
         this.setProcessed(true);
         return true;
      }
   }

   @Override
   public IUnitFormatter getFormatter() {
      IUnitFormatter var1 = super.getFormatter();
      if (UnitFormatterUtil.getPresentationByIdentifier(var1, 1L) == null) {
         var1.addPresentation(new bhx(this, 1L, S.L("Source"), true), false);
      }

      if (this.A() && UnitFormatterUtil.getPresentationByIdentifier(var1, 2L) == null) {
         bjr var2 = (bjr)this.getASTElement();
         if (var2 != null) {
            String var3 = (String)var2.getData("IR_CFG_FINAL");
            if (var3 != null) {
               var1.addPresentation(new bhy(this, 2L, "IR-CFG", false, var3), false);
            }
         }
      }

      return var1;
   }

   @Override
   public ITextDocument getSourceDocument() {
      return new JavaDocument(this);
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

   public static int pC(long var0) {
      return (int)(var0 >>> 56);
   }

   public static int A(long var0) {
      return (int)var0;
   }

   @Override
   public Object getItemObject(long var1) {
      return this.kS.getItemObject(var1);
   }

   @Override
   public List getGlobalActions() {
      return this.wS.getGlobalActions();
   }

   @Override
   public List getItemActions(long var1) {
      return this.wS.getItemActions(var1);
   }

   @Override
   public boolean isValidAddress(String var1) {
      return this.wS.isValidAddress(var1);
   }

   @Override
   public List getAddressActions(String var1) {
      return this.wS.getAddressActions(var1);
   }

   @Override
   public String getAddressOfItem(long var1) {
      return this.wS.getAddressOfItem(var1);
   }

   @Override
   public List getRelatedItems(long var1) {
      return this.wS.getRelatedItems(var1);
   }

   @Override
   public long getItemAtAddress(String var1) {
      return this.wS.getItemAtAddress(var1);
   }

   @Override
   public boolean canExecuteAction(ActionContext var1) {
      int var2 = var1.getActionId();
      if (var2 == 4) {
         long var3 = var1.getItemId();
         if (pC(var3) == 16) {
            return true;
         }
      } else if (var2 == 6) {
         long var7 = var1.getItemId();
         long var5 = pC(var7);
         if (var5 == 10L || var5 == 5L) {
            return true;
         }
      } else if (var2 == 17) {
         return true;
      }

      return this.wS.canExecuteAction(var1);
   }

   @Override
   public boolean prepareExecution(ActionContext var1, IActionData var2) {
      int var3 = var1.getActionId();
      long var4 = var1.getItemId();
      if (var3 == 4 && pC(var4) == 16) {
         int var6 = A(var4);
         bix var7 = this.kS.A().wS().pC(var6);
         if (var7 != null && var7.isString()) {
            String var8 = var7.getString();
            bfx var9 = this.wS.pC(var8);
            if (var9 != null) {
               var4 = this.wS.pC(var9);
               var1.setItemId(var4);
               return this.wS.prepareExecution(var1, var2);
            }
         }
      }

      if (var3 == 4) {
         synchronized (this.UT) {
            List var20 = (List)this.UT.get(var4);
            if (var20 != null && this.wS != null) {
               ActionXrefsData var23 = (ActionXrefsData)var2;
               ArrayList var25 = new ArrayList();
               ArrayList var26 = new ArrayList();

               for (ICodeCoordinates var12 : var20) {
                  if (var12 != null) {
                     String var13 = this.wS.A().pC(var12, true, true);
                     if (var13 != null) {
                        var25.add(var13);
                        String var14 = this.wS.A().pC(var12, true, false);
                        var26.add(var14);
                     }
                  }
               }

               var23.setAddresses(var25);
               var23.setUserAddresses(var26);
               return true;
            }
         }
      }

      if (var3 != 17) {
         return this.wS.prepareExecution(var1, var2);
      } else {
         ActionCollapseData var19 = (ActionCollapseData)var2;
         if (!var19.isNoInfoRequest()) {
            Map var21 = var19.getRecords();

            for (String var24 : new HashSet(var21.keySet())) {
               com.pnfsoftware.jeb.corei.parsers.dex.Sv var10 = this.wS.NS(var24);
               if (var10 != null) {
                  boolean var11 = this.kS.pC(var10);
                  var21.put(var24, var11);
               }
            }
         }

         return true;
      }
   }

   @Override
   public boolean executeAction(ActionContext var1, IActionData var2) {
      return this.executeAction(var1, var2, true);
   }

   @Override
   public boolean executeAction(ActionContext var1, IActionData var2, boolean var3) {
      int var4 = var1.getActionId();
      if (var4 == 17) {
         ActionCollapseData var5 = (ActionCollapseData)var2;
         int var6 = 0;
         Map var7 = var5.getRecords();

         for (Entry var9 : var7.entrySet()) {
            String var10 = (String)var9.getKey();
            com.pnfsoftware.jeb.corei.parsers.dex.Sv var11 = this.wS.NS(var10);
            if (var11 != null) {
               Boolean var12 = (Boolean)var9.getValue();
               if (var12 == null) {
                  var12 = !this.kS.pC(var11);
               }

               if (this.kS.pC(var11, var12.booleanValue())) {
                  var6++;
               }
            }
         }

         this.pC(var6 > 0);
         return true;
      } else {
         return this.wS.executeAction(var1, var2, var3);
      }
   }

   public void pC(boolean var1, UnitChangeEventData var2) {
      if (var1) {
         this.notifyListeners(new JebEvent(J.UnitChange, var2));
      }
   }

   public void pC(boolean var1) {
      this.pC(var1, null);
   }

   @Override
   public IMetadataManager getMetadataManager() {
      return null;
   }

   @Override
   public Map getInlineComments() {
      return this.wS.vP(this.E);
   }

   @Override
   public String getInlineComment(String var1) {
      return this.wS.getInlineComment(var1);
   }

   @Override
   public boolean setInlineComment(String var1, String var2) {
      return this.wS.setInlineComment(var1, var2);
   }

   public DexCommentManager kS() {
      return this.wS.getCommentManager();
   }

   @Override
   public Map getAddressLabels() {
      return this.wS.xC(this.E);
   }

   @Override
   public String getAddressLabel(String var1) {
      return this.wS.getAddressLabel(var1);
   }

   @Override
   public IInputLocation addressToLocation(String var1) {
      return this.wS.addressToLocation(var1);
   }

   @Override
   public String locationToAddress(IInputLocation var1) {
      return this.wS.locationToAddress(var1);
   }

   @Override
   public String getFullyQualifiedName() {
      IJavaElement var1 = this.getASTElement();
      if (var1 instanceof bjl) {
         return ((bjl)var1).getName();
      } else {
         return var1 instanceof bjr ? ((bjr)var1).getName() : null;
      }
   }

   @Override
   public void recordIdentifierPositions(long var1, ICodeCoordinates var3) {
      synchronized (this.UT) {
         Object var5 = (List)this.UT.get(var1);
         if (var5 == null) {
            var5 = new ArrayList();
            this.UT.put(var1, var5);
         }

         if (!var5.contains(var3)) {
            var5.add(var3);
         }
      }
   }

   @Override
   public List getContributions() {
      return ((IUnit)this.getParent()).getContributions();
   }

   @Override
   public void dispose() {
      this.kS.resetDecompilation(this.E);
      if (this.wS != null && this.sY != null) {
         this.wS.removeListener(this.sY);
         this.sY = null;
      }

      super.dispose();
   }
}
