package com.alugarei.cielo.domain

import scala.xml.{XML, Node}

/**
 * Created by Gustavo on 03/02/2016.
 */
trait XmlDeserializable {

  type T

  def fromXml(xml: Node) : T

}
