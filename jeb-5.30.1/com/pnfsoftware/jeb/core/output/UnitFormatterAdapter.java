package com.pnfsoftware.jeb.core.output;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Ser
public class UnitFormatterAdapter implements IUnitFormatter {
   private static final ILogger logger = GlobalLog.getLogger(UnitFormatterAdapter.class);
   @SerId(1)
   private List persistedPres = new ArrayList();
   @SerTransient
   private List transientPres = new ArrayList();
   @SerTransient
   private List allPres = new ArrayList();
   @SerTransient
   private boolean disposed;

   @SerCustomInitPostGraph
   private void init() {
      this.transientPres = new ArrayList();
      this.allPres = new ArrayList(this.persistedPres);
   }

   public UnitFormatterAdapter() {
   }

   public UnitFormatterAdapter(IUnitDocumentPresentation var1) {
      this.addDocumentPresentation(var1);
   }

   public void addDocumentPresentation(IUnitDocumentPresentation var1) {
      this.addPresentation(var1, false);
   }

   @Override
   public int getPresentationCount() {
      return this.allPres.size();
   }

   @Override
   public IUnitDocumentPresentation getPresentation(int var1) {
      IUnitDocumentPresentation var2 = (IUnitDocumentPresentation)this.allPres.get(var1);
      if (var2 == null) {
         throw new RuntimeException();
      } else {
         return var2;
      }
   }

   @Override
   public void addPresentation(IUnitDocumentPresentation var1, boolean var2) {
      this.insertPresentation(this.allPres.size(), var1, var2);
   }

   @Override
   public void insertPresentation(int var1, IUnitDocumentPresentation var2, boolean var3) {
      if (this.allPres.contains(var2)) {
         logger.warn(S.L("This presentation is already present in the formatter"));
      } else {
         long var4 = var2.getId();

         for (IUnitDocumentPresentation var7 : this.allPres) {
            if (var7.getId() == var4) {
               throw new IllegalArgumentException(
                  Strings.ff(
                     "The presentation that is being inserted has ID %d, which is already used by another presentation in this formatter (%s)", var4, var7
                  )
               );
            }
         }

         List var11 = var3 ? this.persistedPres : this.transientPres;
         int var12 = 0;

         for (int var8 = var1 - 1; var8 >= 0; var8--) {
            IUnitDocumentPresentation var9 = (IUnitDocumentPresentation)this.allPres.get(var8);
            int var10 = var11.indexOf(var9);
            if (var10 >= 0) {
               var12 = var10 + 1;
               break;
            }
         }

         var11.add(var12, var2);
         this.allPres.add(var1, var2);
      }
   }

   @Override
   public void removePresentation(int var1) {
      IUnitDocumentPresentation var2 = this.getPresentation(var1);
      if (this.persistedPres.contains(var2)) {
         this.persistedPres.remove(var2);
      } else {
         if (!this.transientPres.contains(var2)) {
            throw new RuntimeException();
         }

         this.transientPres.remove(var2);
      }

      this.allPres.remove(var1);
   }

   @Override
   public boolean isPersisted(int var1) {
      IUnitDocumentPresentation var2 = this.getPresentation(var1);
      return this.persistedPres.contains(var2);
   }

   @Override
   public List getPresentations() {
      return Collections.unmodifiableList(this.allPres);
   }

   @Override
   public void discardTransientPresentations() {
      if (!this.disposed) {
         for (IUnitDocumentPresentation var2 : this.transientPres) {
            var2.dispose();
         }
      }

      this.disposed = true;
   }
}
