#!/usr/local/bin/perl
#
# composite series of images over a background image
#



$n = $ARGV[0];
$k = $ARGV[1];

sub combination {
    my( $n, $r ) = @_;
    return unless defined $n && $n =~ /^\d+$/ && defined $r && $r =~ /^\d+$/;
    my $product = 1;
    while( $r > 0 ) {
        $product *= $n--;
        $product /= $r--;
    }
    return $product;
}
my $all=0;
for ($i=2; $i <= $n; $i++) {
	for ($j=0; $j <= $k; $j++)
		{$all += combination($i-2+$j,$j)*(2**$j);}
}
print $all."\n";
print (2**($n-2+$k+1)-1)."\n";
print "\n";
