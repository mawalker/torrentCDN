exec { "apt-get update":
  path => "/usr/bin",
}

package { "nano":
  ensure  => present,
  require => Exec["apt-get update"],
}

package { "openjdk-7-jre-headless":
  ensure  => present,
  require => Exec["apt-get update"],
}
