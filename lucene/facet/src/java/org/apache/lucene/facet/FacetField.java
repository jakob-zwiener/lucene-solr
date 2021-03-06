package org.apache.lucene.facet;

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.util.Arrays;

import org.apache.lucene.document.Document; // javadoc
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;

/** Add an instance of this to your {@link Document} for
 *  every facet label. */
public class FacetField extends Field {
  static final FieldType TYPE = new FieldType();
  static {
    TYPE.setIndexed(true);
    TYPE.freeze();
  }

  /** Dimension for this field. */
  public final String dim;

  /** Path for this field. */
  public final String[] path;

  /** Creates the this from {@code dim} and
   *  {@code path}. */
  public FacetField(String dim, String... path) {
    super("dummy", TYPE);
    verifyLabel(dim);
    for(String label : path) {
      verifyLabel(label);
    }
    this.dim = dim;
    if (path.length == 0) {
      throw new IllegalArgumentException("path must have at least one element");
    }
    this.path = path;
  }

  @Override
  public String toString() {
    return "FacetField(dim=" + dim + " path=" + Arrays.toString(path) + ")";
  }

  /** Verifies the label is not null or empty string.
   * 
   *  @lucene.internal */
  public static void verifyLabel(String label) {
    if (label == null || label.isEmpty()) {
      throw new IllegalArgumentException("empty or null components not allowed; got: " + label);
    }
  }
}
