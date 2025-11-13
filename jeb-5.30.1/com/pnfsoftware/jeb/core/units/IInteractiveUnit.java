package com.pnfsoftware.jeb.core.units;

import com.pnfsoftware.jeb.core.actions.ActionContext;
import com.pnfsoftware.jeb.core.actions.IActionData;
import com.pnfsoftware.jeb.core.input.IInputLocation;
import com.pnfsoftware.jeb.core.units.impl.AbstractCommentManager;
import com.pnfsoftware.jeb.core.units.impl.Comment;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

@Ser
public interface IInteractiveUnit extends IAddressableUnit {
   List getGlobalActions();

   List getItemActions(long var1);

   List getAddressActions(String var1);

   default IInputLocation addressToLocation(String var1) {
      return null;
   }

   default String locationToAddress(IInputLocation var1) {
      return null;
   }

   boolean canExecuteAction(ActionContext var1);

   boolean prepareExecution(ActionContext var1, IActionData var2);

   boolean executeAction(ActionContext var1, IActionData var2);

   boolean executeAction(ActionContext var1, IActionData var2, boolean var3);

   default Map getInlineComments() {
      return Collections.emptyMap();
   }

   default String getInlineComment(String var1) {
      return null;
   }

   default boolean setInlineComment(String var1, String var2) {
      return false;
   }

   default AbstractCommentManager getCommentManager() {
      return null;
   }

   default String getFullComment(String var1) {
      if (this.getCommentManager() == null) {
         return this.getInlineComment(var1);
      } else {
         Comment var2 = this.getCommentManager().getComment(var1);
         return var2 == null ? null : var2.formatRaw();
      }
   }

   default Map getFullComments() {
      if (this.getCommentManager() == null) {
         return this.getInlineComments();
      } else {
         HashMap var1 = new HashMap();

         for (Entry var3 : this.getCommentManager().getComments().entrySet()) {
            var1.put((String)var3.getKey(), ((Comment)var3.getValue()).formatRaw());
         }

         return var1;
      }
   }

   IMetadataManager getMetadataManager();
}
