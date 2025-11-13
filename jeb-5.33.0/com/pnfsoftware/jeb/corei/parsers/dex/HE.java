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
import com.pnfsoftware.jebglobal.bfs;
import com.pnfsoftware.jebglobal.bft;
import com.pnfsoftware.jebglobal.bfu;
import com.pnfsoftware.jebglobal.bfy;
import com.pnfsoftware.jebglobal.bgd;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Ser
public class HE implements ICodeNode {
   @SerId(1)
   private HE pC;
   @SerId(2)
   private List A;
   @SerId(3)
   private Sv kS;
   @SerId(4)
   private volatile boolean wS;

   @SerCustomInitPostGraph
   private void wS() {
      if (this.A instanceof ArrayList) {
         try {
            this.A = new HashedList(this.A);
         } catch (Exception var1) {
         }
      }
   }

   public HE(Sv var1) {
      this.kS = var1;
   }

   public void pC(boolean var1) {
      this.wS = var1;
   }

   @Override
   public int getInitialExpansion() {
      return this.wS ? 1 : 0;
   }

   @Override
   public String getLabel() {
      String var1 = this.pC();
      if (this.kS.pC.wS() && this.kS instanceof bfu) {
         bgd var2 = ((bfu)this.kS).A();
         if (var2 != null && var2.A() != null) {
            int var3 = var2.A().getInstructions().size();
            var1 = var1 + Strings.ff("  /%d", var3);
         }
      }

      return var1;
   }

   String pC() {
      if (this.kS.pC.kS()) {
         if (this.kS instanceof bfu var6) {
            return var6.getSignature(true, false, true, false);
         }

         if (this.kS instanceof bft var5) {
            return var5.getSignature(true, false, true, false);
         }

         if (this.kS instanceof bfs && ((bfs)this.kS).isAnonymousClass()) {
            bfs var1 = (bfs)this.kS;

            try {
               bfy var2;
               if (var1.getInterfaceTypeIndexes().length == 0) {
                  var2 = this.kS.pC.A(var1.getSupertypeSignature(false));
               } else {
                  var2 = this.kS.pC.wS(var1.getInterfaceTypeIndexes()[0]);
               }

               String var3 = var2.getSignature(true, false, false);
               return Strings.ff("new %s() {...}", var3);
            } catch (Exception var4) {
               JebCoreService.notifySilentExceptionToClient(var4, Maps.toMap("class_effective_name", this.kS.getName(true)));
            }
         }
      } else {
         if (this.kS instanceof bfu) {
            return ((bfu)this.kS).A(true);
         }

         if (this.kS instanceof bft) {
            return ((bft)this.kS).A(true);
         }
      }

      return this.kS.getName(true);
   }

   @Override
   public String[] getAdditionalLabels() {
      return null;
   }

   public HE A() {
      return this.pC;
   }

   public synchronized void pC(HE var1) {
      if (var1 != this.pC) {
         if (this.pC != null) {
            this.pC.kS(this);
         }

         this.pC = var1;
         if (var1 != null) {
            var1.A(this);
         }
      }
   }

   @Override
   public boolean hasChildren() {
      return this.A == null ? false : !this.A.isEmpty();
   }

   @Override
   public int getCountOfChildren() {
      return this.A == null ? 0 : this.A.size();
   }

   @Override
   public List getChildren() {
      return this.A == null ? Collections.emptyList() : Collections.unmodifiableList(this.A);
   }

   public HE pC(String var1) {
      return this.pC(var1, true);
   }

   public synchronized HE pC(String var1, boolean var2) {
      if (this.A == null) {
         return null;
      } else {
         for (HE var4 : this.A) {
            Sv var5 = var4.kS();
            if (var5 instanceof qt && ((qt)var5).getName(var2).equals(var1)) {
               return var4;
            }
         }

         return null;
      }
   }

   public synchronized HE pC(ICodeItem var1) {
      if (var1 == this.kS()) {
         return this;
      } else if (this.A == null) {
         return null;
      } else {
         for (HE var3 : this.A) {
            HE var4 = var3.pC(var1);
            if (var4 != null) {
               return var4;
            }
         }

         return null;
      }
   }

   public synchronized void A(HE var1) {
      if (this.A == null) {
         this.A = new HashedList(10);
      }

      if (!this.A.contains(var1)) {
         if (var1.pC != null && var1.pC != this) {
            throw new JebRuntimeException("Parent of child is already set");
         } else {
            this.A.add(var1);
            var1.pC = this;
         }
      }
   }

   public synchronized void kS(HE var1) {
      if (this.A != null) {
         if (this.A.contains(var1)) {
            this.A.remove(var1);
            var1.pC = null;
         }
      }
   }

   public Sv kS() {
      return this.kS;
   }

   @Override
   public ItemClassIdentifiers getClassId() {
      return null;
   }

   @Override
   public long getItemId() {
      return this.kS.getItemId();
   }

   @Override
   public int getItemFlags() {
      return 0;
   }

   @Override
   public String toString() {
      return Strings.ff("[%s]", this.kS);
   }
}
