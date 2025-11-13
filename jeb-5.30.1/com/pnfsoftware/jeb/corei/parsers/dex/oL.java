package com.pnfsoftware.jeb.corei.parsers.dex;

import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.exceptions.JebRuntimeException;
import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.output.tree.ICodeNode;
import com.pnfsoftware.jeb.core.units.code.ICodeItem;
import com.pnfsoftware.jeb.util.collect.HashedList;
import com.pnfsoftware.jeb.util.collect.Maps;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jebglobal.bjn;
import com.pnfsoftware.jebglobal.bjo;
import com.pnfsoftware.jebglobal.bjp;
import com.pnfsoftware.jebglobal.bjt;
import com.pnfsoftware.jebglobal.bjy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Ser
public class oL implements ICodeNode {
   @SerId(1)
   private oL q;
   @SerId(2)
   private List RF;
   @SerId(3)
   private CU xK;
   @SerId(4)
   private volatile boolean Dw;

   @SerCustomInitPostGraph
   private void Dw() {
      if (this.RF instanceof ArrayList) {
         try {
            this.RF = new HashedList(this.RF);
         } catch (Exception var1) {
         }
      }
   }

   public oL(CU var1) {
      this.xK = var1;
   }

   public void q(boolean var1) {
      this.Dw = var1;
   }

   @Override
   public int getInitialExpansion() {
      return this.Dw ? 1 : 0;
   }

   @Override
   public String getLabel() {
      String var1 = this.q();
      if (this.xK.q.za() && this.xK instanceof bjp) {
         bjy var2 = ((bjp)this.xK).RF();
         if (var2 != null && var2.RF() != null) {
            int var3 = var2.RF().getInstructions().size();
            var1 = var1 + Strings.ff("  /%d", var3);
         }
      }

      return var1;
   }

   String q() {
      if (this.xK.q.gP()) {
         if (this.xK instanceof bjp var6) {
            return var6.getSignature(true, false, true, false);
         }

         if (this.xK instanceof bjo var5) {
            return var5.getSignature(true, false, true, false);
         }

         if (this.xK instanceof bjn && ((bjn)this.xK).isAnonymousClass()) {
            bjn var1 = (bjn)this.xK;

            try {
               bjt var2;
               if (var1.getInterfaceTypeIndexes().length == 0) {
                  var2 = this.xK.q.RF(var1.getSupertypeSignature(false));
               } else {
                  var2 = this.xK.q.Dw(var1.getInterfaceTypeIndexes()[0]);
               }

               String var3 = var2.getSignature(true, false, false);
               return Strings.ff("new %s() {...}", var3);
            } catch (Exception var4) {
               JebCoreService.notifySilentExceptionToClient(var4, Maps.toMap("class_effective_name", this.xK.getName(true)));
            }
         }
      } else {
         if (this.xK instanceof bjp) {
            return ((bjp)this.xK).RF(true);
         }

         if (this.xK instanceof bjo) {
            return ((bjo)this.xK).RF(true);
         }
      }

      return this.xK.getName(true);
   }

   @Override
   public String[] getAdditionalLabels() {
      return null;
   }

   public oL RF() {
      return this.q;
   }

   public synchronized void q(oL var1) {
      if (var1 != this.q) {
         if (this.q != null) {
            this.q.xK(this);
         }

         this.q = var1;
         if (var1 != null) {
            var1.RF(this);
         }
      }
   }

   @Override
   public boolean hasChildren() {
      return this.RF == null ? false : !this.RF.isEmpty();
   }

   @Override
   public int getCountOfChildren() {
      return this.RF == null ? 0 : this.RF.size();
   }

   @Override
   public List getChildren() {
      return this.RF == null ? Collections.emptyList() : Collections.unmodifiableList(this.RF);
   }

   public oL q(String var1) {
      return this.q(var1, true);
   }

   public synchronized oL q(String var1, boolean var2) {
      if (this.RF == null) {
         return null;
      } else {
         for (oL var4 : this.RF) {
            CU var5 = var4.xK();
            if (var5 instanceof Vj && ((Vj)var5).getName(var2).equals(var1)) {
               return var4;
            }
         }

         return null;
      }
   }

   public synchronized oL q(ICodeItem var1) {
      if (var1 == this.xK()) {
         return this;
      } else if (this.RF == null) {
         return null;
      } else {
         for (oL var3 : this.RF) {
            oL var4 = var3.q(var1);
            if (var4 != null) {
               return var4;
            }
         }

         return null;
      }
   }

   public synchronized void RF(oL var1) {
      if (this.RF == null) {
         this.RF = new HashedList(10);
      }

      if (!this.RF.contains(var1)) {
         if (var1.q != null && var1.q != this) {
            throw new JebRuntimeException("Parent of child is already set");
         } else {
            this.RF.add(var1);
            var1.q = this;
         }
      }
   }

   public synchronized void xK(oL var1) {
      if (this.RF != null) {
         if (this.RF.contains(var1)) {
            this.RF.remove(var1);
            var1.q = null;
         }
      }
   }

   public CU xK() {
      return this.xK;
   }

   @Override
   public ItemClassIdentifiers getClassId() {
      return null;
   }

   @Override
   public long getItemId() {
      return this.xK.getItemId();
   }

   @Override
   public int getItemFlags() {
      return 0;
   }

   @Override
   public String toString() {
      return Strings.ff("[%s]", this.xK);
   }
}
