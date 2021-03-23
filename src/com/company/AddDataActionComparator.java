/*
 *
 * Copyright (c) 1999-2015 Luciad All Rights Reserved.
 *
 * Luciad grants you ("Licensee") a non-exclusive, royalty free, license to use,
 * modify and redistribute this software in source and binary code form,
 * provided that i) this copyright notice and license appear on all copies of
 * the software; and ii) Licensee does not utilize the software in a manner
 * which is disparaging to Luciad.
 *
 * This software is provided "AS IS," without a warranty of any kind. ALL
 * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING ANY
 * IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE OR
 * NON-INFRINGEMENT, ARE HEREBY EXCLUDED. LUCIAD AND ITS LICENSORS SHALL NOT BE
 * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING
 * OR DISTRIBUTING THE SOFTWARE OR ITS DERIVATIVES. IN NO EVENT WILL LUCIAD OR ITS
 * LICENSORS BE LIABLE FOR ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT,
 * INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER
 * CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF THE USE OF
 * OR INABILITY TO USE SOFTWARE, EVEN IF LUCIAD HAS BEEN ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGES.
 */
package com.company;

import java.util.Comparator;
import java.util.Iterator;

/**
 * Se ha testeado y esta clase no PUede accederse a las capas del mapa
 * Comparator for {@link AddDataAction AddDataAction} objects
 */
public class AddDataActionComparator implements Comparator<AddDataAction> {
  @Override
  public int compare(AddDataAction aAddDataAction, AddDataAction anotherAddDataAction) {
    Iterator<String> iterator = aAddDataAction.getMenuItemList().iterator();
    Iterator<String> otherIterator = anotherAddDataAction.getMenuItemList().iterator();
    while (iterator.hasNext() && otherIterator.hasNext()) {
      int compare = iterator.next().compareTo(otherIterator.next());
      if (compare != 0) {

        return compare;
      }
    }
    if (iterator.hasNext()) {
      return -1;
    } else if (otherIterator.hasNext()) {
      return 1;
    } else {
      return aAddDataAction.getName().compareTo(anotherAddDataAction.getName());
    }
  }
}
