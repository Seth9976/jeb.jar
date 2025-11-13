package com.pnfsoftware.jeb.core.events;

public class QuestionNotificationPath extends AbstractQuestionNotification {
   private QuestionNotificationPath.Type pathType;

   public QuestionNotificationPath(QuestionNotificationPath.Type var1, String var2, String var3) {
      super(var2, var3, false);
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.pathType = var1;
      }
   }

   public QuestionNotificationPath.Type getPathType() {
      return this.pathType;
   }

   public static enum Type {
      SAVE_TO_FILE,
      SELECT_FILE,
      SELECT_FOLDER;
   }
}
